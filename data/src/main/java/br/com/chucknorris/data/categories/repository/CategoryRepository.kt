package br.com.data.categories.repository

import br.com.data.categories.remote.ICategoryRemoteData
import br.com.domain.category.ICategoryRepository
import br.com.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

class CategoryRepository (private val remoteDate : ICategoryRemoteData) : ICategoryRepository {
    override fun getCategories(): Single<Categories> {
        return remoteDate.getCategories()
    }
}