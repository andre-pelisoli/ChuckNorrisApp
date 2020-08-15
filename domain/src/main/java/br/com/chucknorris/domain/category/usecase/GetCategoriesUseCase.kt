package br.com.chucknorris.domain.category.usecase

import br.com.chucknorris.domain.category.ICategoryRepository
import br.com.chucknorris.domain.category.model.Categories
import io.reactivex.rxjava3.core.Single

class GetCategoriesUseCase (private val repository: ICategoryRepository) {

    fun getCategories () : Single<Categories> {
        return repository.getCategories()
    }
}