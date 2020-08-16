package br.com.chucknorris.data.categories.client

import retrofit2.http.GET

interface ICategoryApiClient {

    @GET("/jokes/categories")
    suspend fun getCategories() : List<String>

}