package br.com.chucknorris.base.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


open class BasePresenter<T> : BaseMvp.MvpPresenter<T> {
    var view: T? = null

    private val job = Job()

    val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    override fun attachView(mvpView: T?) {
        view = mvpView
    }

    override fun detachView() {
        job.cancel()
        attachView(null)
    }

}