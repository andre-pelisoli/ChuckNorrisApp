package br.com.chucknorris.data.joke.remote

import br.com.chucknorris.data.joke.client.IJokeApiClient
import br.com.chucknorris.domain.joke.model.Joke

class JokeRemoteData(private val apiClient : IJokeApiClient) : IJokeRemoteData {
    override suspend fun getJoke(category: String): Joke {
        return apiClient.getJoke(category).toJoke()
    }
}