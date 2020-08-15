package br.com.chucknorris.data.categories.client

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ICategoryApiClient {

    @GET("/jokes/categories")
    fun getCategories() : Single<List<String>>

}