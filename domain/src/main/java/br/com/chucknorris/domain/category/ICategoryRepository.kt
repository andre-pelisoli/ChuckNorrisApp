package br.com.domain.category

import br.com.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

interface ICategoryRepository {

    fun getCategories() : Single<Categories>

}