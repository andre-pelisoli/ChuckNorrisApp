package br.com.data.categories.remote

import br.com.chucknorris.data.categories.remote.ICategoryRemoteData
import br.com.chucknorris.data.categories.client.ICategoryApiClient
import br.com.chucknorris.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

class CategoryRemoteData(private val apiClient : ICategoryApiClient) : ICategoryRemoteData {
    override fun getCategories(): Single<Categories> {
        return apiClient.getCategories().map { Categories(it) }
    }
}