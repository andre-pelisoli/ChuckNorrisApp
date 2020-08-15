package br.com.chucknorris.domain.joke

import br.com.chucknorris.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

interface IJokeRepository {

    fun getJoke(category : String) : Single<Joke>

}