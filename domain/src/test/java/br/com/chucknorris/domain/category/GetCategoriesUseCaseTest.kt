package br.com.chucknorris.domain.category

import br.com.chucknorris.domain.category.ICategoryRepository
import br.com.chucknorris.domain.category.model.Categories
import br.com.domain.category.usecase.GetCategoriesUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import java.lang.Exception


class GetCategoriesUseCaseTest {
    @RelaxedMockK
    lateinit var repository: ICategoryRepository

    @RelaxedMockK
    lateinit var categories : Categories

    private lateinit var categoriesUseCase : GetCategoriesUseCase


    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        categoriesUseCase =  GetCategoriesUseCase(repository)
    }

    @Test
    fun `Success on getting categories`() {
        every { repository.getCategories() } returns Single.just(categories)

        categoriesUseCase.getCategories().test().assertValue(categories)

    }

    @Test
    fun `Error on getting categories`() {
        val exception = Exception("testing exception")

        every { repository.getCategories() } returns Single.error(exception)

        categoriesUseCase.getCategories().test().assertError(exception)
    }
}