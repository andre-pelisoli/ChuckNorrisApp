package br.com.domain.joke

import br.com.domain.joke.model.Joke
import br.com.domain.joke.usecase.GetJokeUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test


class GetJokeUseCaseTest {
    @RelaxedMockK
    lateinit var repository: IJokeRepository

    @RelaxedMockK
    lateinit var joke : Joke

    private lateinit var jokeUseCase : GetJokeUseCase

    private val categoryName = "science"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        jokeUseCase =  GetJokeUseCase(repository)
    }

    @Test
    fun `Success on getting joke`() {
        every { repository.getJoke(categoryName) } returns Single.just(joke)

        jokeUseCase.getJoke(categoryName).test().assertValue(joke)
        jokeUseCase.getJoke(categoryName).test().assertNoErrors()

    }

    @Test
    fun `Error on getting joke`() {
        val exception = Exception("testing exception")

        every { repository.getJoke(categoryName) } returns Single.error(exception)

        jokeUseCase.getJoke(categoryName).test().assertError(exception)
    }

    @Test
    fun `Empty category Name`() {
        jokeUseCase.getJoke("").test().assertError(Exception::class.java)
    }
}