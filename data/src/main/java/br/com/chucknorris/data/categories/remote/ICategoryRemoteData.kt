package br.com.chucknorris.data.categories.remote

import br.com.chucknorris.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

interface ICategoryRemoteData {
    fun getCategories() : Single<Categories>
}