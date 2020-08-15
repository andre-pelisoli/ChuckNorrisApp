package br.com.chucknorris.category.presenter

import br.com.chucknorris.base.mvp.BasePresenter
import br.com.chucknorris.base.rx.scheduler.ISchedulerProvider
import br.com.chucknorris.domain.category.usecase.GetCategoriesUseCase

class CategoryPresenter(val scheduler: ISchedulerProvider, private val loadCategoriesUseCase : GetCategoriesUseCase) : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    override fun loadCategories() {
        view?.showLoading()

        addSubscription(loadCategoriesUseCase
            .getCategories()
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe (
                { categories ->
                    view?.disableLoading()
                    view?.showCategories(categories)
                },
                { t: Throwable? ->
                    view?.disableLoading()
                    view?.showError()
                }
            ))
    }
}