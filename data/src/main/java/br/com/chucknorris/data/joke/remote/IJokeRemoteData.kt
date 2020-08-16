package br.com.chucknorris.data.joke.remote

import br.com.chucknorris.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

interface IJokeRemoteData {
    suspend fun getJoke(category : String) : Joke
}