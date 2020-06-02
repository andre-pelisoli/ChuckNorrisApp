package br.com.data.joke.remote

import br.com.data.joke.client.IJokeApiClient
import br.com.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

class JokeRemoteData(private val apiClient : IJokeApiClient) : IJokeRemoteData {
    override fun getJoke(category: String): Single<Joke> {
        return apiClient.getJoke(category).map { it.toJoke() }
    }
}