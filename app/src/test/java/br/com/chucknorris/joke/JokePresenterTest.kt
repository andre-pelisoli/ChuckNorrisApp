package br.com.chucknorris.joke

import br.com.chucknorris.base.rx.scheduler.AppSchedulerProvider
import br.com.chucknorris.joke.presenter.JokeContract
import br.com.chucknorris.joke.presenter.JokePresenter
import br.com.domain.joke.model.Joke
import br.com.domain.joke.usecase.GetJokeUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test


class JokePresenterTest {

    @RelaxedMockK
    lateinit var view: JokeContract.View

    @RelaxedMockK
    private lateinit var joke : Joke

    @RelaxedMockK
    private lateinit var getJokeUseCase: GetJokeUseCase

    private val categoryName = "science"
    private lateinit var presenter : JokeContract.Presenter
    private lateinit var schedulerProvider: AppSchedulerProvider
    private lateinit var testScheduler : TestScheduler

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        testScheduler = TestScheduler()

        schedulerProvider = AppSchedulerProvider(testScheduler, testScheduler)
        presenter =  JokePresenter(schedulerProvider, getJokeUseCase)
        presenter.attachView(view)
    }

    @Test
    fun `Success on loading categories`() {
        every { getJokeUseCase.getJoke(categoryName) } returns Single.just(joke)

        presenter.loadJoke(categoryName)
        testScheduler.triggerActions()

        verify { view.showLoading() }
        verify { view.disableLoading() }
        verify { view.showJoke(joke) }
    }

    @Test
    fun `Error on loading categories`() {
        every { getJokeUseCase.getJoke(categoryName) } returns Single.error(Exception())

        presenter.loadJoke(categoryName)
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