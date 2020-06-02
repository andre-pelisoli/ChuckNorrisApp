package br.com.domain.joke

import br.com.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

interface IJokeRepository {

    fun getJoke(category : String) : Single<Joke>

}