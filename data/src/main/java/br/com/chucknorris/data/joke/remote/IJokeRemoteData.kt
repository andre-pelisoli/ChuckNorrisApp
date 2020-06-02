package br.com.data.joke.remote

import br.com.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

interface IJokeRemoteData {
    fun getJoke(category : String) : Single<Joke>
}