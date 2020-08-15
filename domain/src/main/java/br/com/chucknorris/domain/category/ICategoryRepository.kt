package br.com.chucknorris.domain.category

import br.com.chucknorris.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

interface ICategoryRepository {

    fun getCategories() : Single<Categories>

}