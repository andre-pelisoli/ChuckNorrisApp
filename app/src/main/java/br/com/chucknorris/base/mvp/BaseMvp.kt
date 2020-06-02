package br.com.chucknorris.base.mvp

class BaseMvp {

    interface MvpPresenter<V> {
        fun attachView(mvpView: V?)
        fun detachView()
    }

    interface View
}