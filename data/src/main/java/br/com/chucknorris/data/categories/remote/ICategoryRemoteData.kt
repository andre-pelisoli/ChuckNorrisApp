package br.com.data.categories.remote

import br.com.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

interface ICategoryRemoteData {
    fun getCategories() : Single<Categories>
}