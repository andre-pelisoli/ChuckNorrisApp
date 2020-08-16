package br.com.chucknorris.category.presenter

import android.util.Log
import br.com.chucknorris.base.mvp.BasePresenter
import br.com.chucknorris.domain.category.model.Categories
import br.com.chucknorris.domain.category.usecase.GetCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryPresenter(private val loadCategoriesUseCase : GetCategoriesUseCase) : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    override fun loadCategories() {
        coroutineScope.launch(Dispatchers.Main) {
            try {
                val categories :  Categories

                view?.showLoading()

                withContext(Dispatchers.IO) {
                    categories = loadCategoriesUseCase.getCategories()
                }

                view?.disableLoading()
                view?.showCategories(categories)
            } catch (exception : Exception) {
                view?.disableLoading()
                view?.showError()
            }
        }
    }
}