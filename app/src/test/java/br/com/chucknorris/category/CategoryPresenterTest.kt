package br.com.chucknorris.category

import br.com.chucknorris.base.rx.scheduler.AppSchedulerProvider
import br.com.chucknorris.category.presenter.CategoryContract
import br.com.chucknorris.category.presenter.CategoryPresenter
import br.com.chucknorris.domain.category.model.Categories
import br.com.domain.category.usecase.GetCategoriesUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception


class CategoryPresenterTest {

    @RelaxedMockK
    lateinit var view: CategoryContract.View

    @RelaxedMockK
    private lateinit var categories : Categories

    @RelaxedMockK
    private lateinit var getCategoriesUseCase : GetCategoriesUseCase

    private lateinit var presenter : CategoryPresenter
    private lateinit var schedulerProvider: AppSchedulerProvider
    private lateinit var testScheduler : TestScheduler

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        testScheduler = TestScheduler()

        schedulerProvider = AppSchedulerProvider(testScheduler, testScheduler)
        presenter =  CategoryPresenter(schedulerProvider, getCategoriesUseCase)
        presenter.attachView(view)
    }

    @Test
    fun `Success on loading categories`() {
        every { getCategoriesUseCase.getCategories() } returns Single.just(categories)

        presenter.loadCategories()
        testScheduler.triggerActions()

        verify { view.showLoading() }
        verify { view.disableLoading() }
        verify { view.showCategories(categories) }
    }

    @Test
    fun `Error on loading categories`() {
        every { getCategoriesUseCase.getCategories() } returns Single.error(Exception())

        presenter.loadCategories()
        testScheduler.triggerActions()

        verify { view.showLoading() }
        verify { view.disableLoading() }
        verify { view.showError() }
    }


    @After
    fun detachView () {
        presenter.detachView()
    }
}