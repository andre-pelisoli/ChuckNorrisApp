package br.com.chucknorris.data.categories.repository

import br.com.chucknorris.data.categories.remote.ICategoryRemoteData
import br.com.chucknorris.domain.category.ICategoryRepository
import br.com.chucknorris.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

class CategoryRepository (private val remoteDate : ICategoryRemoteData) : ICategoryRepository {
    override fun getCategories(): Single<Categories> {
        return remoteDate.getCategories()
    }
}