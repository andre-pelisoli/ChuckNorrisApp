package br.com.chucknorris.joke

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import br.com.chucknorris.R
import br.com.chucknorris.base.rx.di.DaggerSchedulerProviderComponent
import br.com.chucknorris.category.CategoryActivity.Companion.CATEGORY_NAME
import br.com.chucknorris.databinding.JokeActivityBinding
import br.com.chucknorris.joke.di.DaggerJokeActivityComponent
import br.com.chucknorris.joke.presenter.JokeContract
import br.com.chucknorris.network.di.DaggerApiServiceComponent
import br.com.chucknorris.domain.joke.model.Joke
import com.bumptech.glide.Glide
import javax.inject.Inject


class JokeActivity : AppCompatActivity(), JokeContract.View {
    @Inject lateinit var presenter : JokeContract.Presenter
    private lateinit var binding: JokeActivityBinding
    private val categorySelected by lazy { intent.getStringExtra(CATEGORY_NAME) ?: "" }
    private var jokeUrl : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JokeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        injectDependencies()

        setupViews()

        presenter.attachView(this)
        presenter.loadJoke(categorySelected)
    }

    private fun setupViews() {
        binding.errorLayout.tryAgainBtn.setOnClickListener {
            binding.errorLayout.errorConstraintLayout.visibility = View.GONE
            presenter.loadJoke(categorySelected)
        }

        binding.goToPageBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(jokeUrl))
            startActivity(browserIntent)
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.errorLayout.errorConstraintLayout.visibility = View.GONE
            binding.contentGroup.visibility = View.GONE
            presenter.loadJoke(categorySelected)
            binding.swipeRefresh.isRefreshing = false

        }

        setToolbar()
    }

    private fun setToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        binding.toolbar.title = getString(R.string.joke)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        binding.toolbar.setNavigationOnClickListener {  NavUtils.navigateUpFromSameTask(this)  }
    }

    private fun injectDependencies() {
        val jokeComponent = DaggerJokeActivityComponent
            .builder()
            .schedulerProviderComponent(DaggerSchedulerProviderComponent.builder().build())
            .apiServiceComponent(DaggerApiServiceComponent.builder().build())
            .build()
        jokeComponent.inject(this)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun disableLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun showJoke(newJoke: Joke) {
        jokeUrl = newJoke.url
        binding.errorLayout.errorConstraintLayout.visibility = View.GONE
        binding.contentGroup.visibility = View.VISIBLE
        binding.jokeTxt.text = newJoke.value
        Glide.with(this).load(newJoke.iconUrl).into(binding.jokeImg);
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun showError() {
        binding.errorLayout.errorConstraintLayout.visibility = View.VISIBLE
        binding.contentGroup.visibility = View.GONE
    }
}