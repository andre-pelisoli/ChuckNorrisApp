package br.com.chucknorris.base.mvp

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


open class BasePresenter<T> : BaseMvp.MvpPresenter<T> {
    var view: T? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(mvpView: T?) {
        view = mvpView
    }

    override fun detachView() {
        compositeDisposable.clear()
        attachView(null)
    }

    fun addSubscription(subscription: Disposable?) {
        compositeDisposable.add(subscription)
    }

}