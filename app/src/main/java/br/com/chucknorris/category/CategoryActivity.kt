package br.com.chucknorris.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.chucknorris.R
import br.com.chucknorris.base.rx.di.DaggerSchedulerProviderComponent
import br.com.chucknorris.category.adapter.CategoryAdapter
import br.com.chucknorris.category.adapter.ICategoryListener
import br.com.chucknorris.category.di.CategoryModule
import br.com.chucknorris.category.di.DaggerCategoryActivityComponent
import br.com.chucknorris.category.presenter.CategoryContract
import br.com.chucknorris.databinding.CategoryActivityBinding
import br.com.chucknorris.joke.JokeActivity
import br.com.chucknorris.network.di.DaggerApiServiceComponent
import br.com.chucknorris.domain.category.model.Categories
import javax.inject.Inject

class CategoryActivity : AppCompatActivity(), CategoryContract.View, ICategoryListener {

    @Inject lateinit var categoriesAdapter : CategoryAdapter
    @Inject lateinit var presenter : CategoryContract.Presenter
    private lateinit var binding: CategoryActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CategoryActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar).apply { title = getString(R.string.categories) }

        injectDependencies()

        setupViews()

        presenter.attachView(this)
        presenter.loadCategories()
    }

    private fun setupViews() {
        binding.categoriesRv.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.categoriesRv.adapter = categoriesAdapter

        binding.errorLayout.tryAgainBtn.setOnClickListener {
            binding.errorLayout.errorConstraintLayout.visibility = View.GONE
            presenter.loadCategories()
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.errorLayout.errorConstraintLayout.visibility = View.GONE
            binding.contentGroup.visibility = View.GONE
            presenter.loadCategories()
            binding.swipeRefresh.isRefreshing = false

        }
    }

    private fun injectDependencies() {
        val categoryComponent = DaggerCategoryActivityComponent
            .builder()
            .schedulerProviderComponent(DaggerSchedulerProviderComponent.builder().build())
            .apiServiceComponent(DaggerApiServiceComponent.builder().build())
            .categoryModule(CategoryModule(this))
            .build()

        categoryComponent.inject(this)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun disableLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showCategories(categories: Categories) {
        binding.errorLayout.errorConstraintLayout.visibility = View.GONE
        binding.contentGroup.visibility = View.VISIBLE
        categoriesAdapter.setCategories(categories.list)
    }

    override fun showError() {
        binding.errorLayout.errorConstraintLayout.visibility = View.VISIBLE
        binding.contentGroup.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun onCategoryClick(categoryName: String) {
        val intent = Intent(applicationContext, JokeActivity::class.java).apply {
            putExtras(bundleOf(CATEGORY_NAME to categoryName))
        }

        startActivity(intent)
    }

    companion object {
        const val CATEGORY_NAME = "categoryName"
    }
}