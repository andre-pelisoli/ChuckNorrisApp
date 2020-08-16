package br.com.chucknorris.data.categories.remote

import br.com.chucknorris.data.categories.client.ICategoryApiClient
import br.com.chucknorris.domain.category.model.Categories

class CategoryRemoteData(private val apiClient : ICategoryApiClient) : ICategoryRemoteData {
    override suspend fun getCategories(): Categories {
        return Categories(apiClient.getCategories())
    }
}