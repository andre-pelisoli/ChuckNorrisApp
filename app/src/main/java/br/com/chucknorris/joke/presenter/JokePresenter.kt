package br.com.chucknorris.joke.presenter

import br.com.chucknorris.base.mvp.BasePresenter
import br.com.chucknorris.base.rx.scheduler.ISchedulerProvider
import br.com.chucknorris.domain.joke.usecase.GetJokeUseCase

class JokePresenter(private val scheduler : ISchedulerProvider, private val loadJokeUseCase: GetJokeUseCase) : BasePresenter<JokeContract.View>(), JokeContract.Presenter {

    override fun loadJoke(categoryName : String) {
        view?.showLoading()

        addSubscription(loadJokeUseCase
            .getJoke(categoryName)
            .subscribeOn(scheduler.backgroundThread())
            .observeOn(scheduler.mainThread())
            .subscribe (
                { joke ->
                    view?.disableLoading()
                    view?.showJoke(joke)
                },
                { t: Throwable? ->
                    view?.disableLoading()
                    view?.showError()
                }
            ))

    }
}