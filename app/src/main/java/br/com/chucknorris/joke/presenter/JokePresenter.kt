package br.com.chucknorris.joke.presenter

import br.com.chucknorris.base.mvp.BasePresenter
import br.com.chucknorris.domain.joke.model.Joke
import br.com.chucknorris.domain.joke.usecase.GetJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class JokePresenter(private val loadJokeUseCase: GetJokeUseCase) : BasePresenter<JokeContract.View>(), JokeContract.Presenter {

    override fun loadJoke(categoryName : String) {
        coroutineScope.launch (Dispatchers.Main) {
            try {
                val joke : Joke

                view?.showLoading()

                withContext(Dispatchers.IO) {
                    joke = loadJokeUseCase.getJoke(categoryName)
                }

                view?.disableLoading()
                view?.showJoke(joke)

            } catch (exception : Exception) {
                view?.disableLoading()
                view?.showError()
            }
        }
    }
}