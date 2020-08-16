package br.com.chucknorris.data.categories.repository

import br.com.chucknorris.data.categories.remote.ICategoryRemoteData
import br.com.chucknorris.domain.category.ICategoryRepository
import br.com.chucknorris.domain.category.model.Categories

class CategoryRepository (private val remoteDate : ICategoryRemoteData) : ICategoryRepository {
    override suspend fun getCategories(): Categories {
        return remoteDate.getCategories()
    }
}