package br.com.data.joke.client

import br.com.data.joke.model.JokeApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IJokeApiClient {

    @GET("/jokes/random?")
    fun getJoke(@Query("category") category : String) : Single<JokeApi>

}