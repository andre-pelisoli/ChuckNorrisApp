package br.com.chucknorris.data.joke.repository

import br.com.chucknorris.data.joke.remote.IJokeRemoteData
import br.com.chucknorris.domain.joke.IJokeRepository
import br.com.chucknorris.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

class JokeRepository (private val remoteDate : IJokeRemoteData) : IJokeRepository {
    override fun getJoke(category : String): Single<Joke> {
        return remoteDate.getJoke(category)
    }
}