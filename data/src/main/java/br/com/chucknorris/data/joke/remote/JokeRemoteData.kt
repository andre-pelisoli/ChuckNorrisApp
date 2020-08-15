package br.com.chucknorris.data.joke.remote

import br.com.chucknorris.data.joke.client.IJokeApiClient
import br.com.chucknorris.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

class JokeRemoteData(private val apiClient : IJokeApiClient) : IJokeRemoteData {
    override fun getJoke(category: String): Single<Joke> {
        return apiClient.getJoke(category).map { it.toJoke() }
    }
}