package br.com.chucknorris.joke.presenter

import br.com.chucknorris.base.mvp.BaseMvp
import br.com.chucknorris.domain.joke.model.Joke

class JokeContract {

        interface View: BaseMvp.View {
            fun showLoading()
            fun disableLoading()
            fun showJoke(newJoke : Joke)
            fun showError()
        }

        interface Presenter: BaseMvp.MvpPresenter<View> {
            fun loadJoke(categoryName : String)
        }
}