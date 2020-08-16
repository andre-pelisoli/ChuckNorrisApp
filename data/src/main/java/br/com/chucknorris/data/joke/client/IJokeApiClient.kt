package br.com.chucknorris.data.joke.client

import br.com.chucknorris.data.joke.model.JokeApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IJokeApiClient {

    @GET("/jokes/random?")
    suspend fun getJoke(@Query("category") category : String) : JokeApi

}