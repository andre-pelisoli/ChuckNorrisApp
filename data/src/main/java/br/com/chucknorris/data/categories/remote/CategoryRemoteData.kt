package br.com.data.categories.remote

import br.com.data.categories.client.ICategoryApiClient
import br.com.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

class CategoryRemoteData(private val apiClient : ICategoryApiClient) : ICategoryRemoteData {
    override fun getCategories(): Single<Categories> {
        return apiClient.getCategories().map { Categories(it) }
    }
}