package br.com.chucknorris.data.joke.repository

import br.com.chucknorris.data.joke.remote.IJokeRemoteData
import br.com.chucknorris.domain.joke.IJokeRepository
import br.com.chucknorris.domain.joke.model.Joke

class JokeRepository (private val remoteDate : IJokeRemoteData) : IJokeRepository {
    override suspend fun getJoke(category : String): Joke {
        return remoteDate.getJoke(category)
    }
}