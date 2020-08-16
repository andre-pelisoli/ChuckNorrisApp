package br.com.chucknorris.joke.di

import br.com.chucknorris.joke.presenter.JokeContract
import br.com.chucknorris.joke.presenter.JokePresenter
import br.com.chucknorris.data.joke.client.IJokeApiClient
import br.com.chucknorris.data.joke.remote.IJokeRemoteData
import br.com.chucknorris.data.joke.remote.JokeRemoteData
import br.com.chucknorris.data.joke.repository.JokeRepository
import br.com.chucknorris.domain.joke.IJokeRepository
import br.com.chucknorris.domain.joke.usecase.GetJokeUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class JokeModule {

    @Provides
    fun providesJokePresenter(
        jokeUseCase: GetJokeUseCase
    ) : JokeContract.Presenter {
        return JokePresenter(jokeUseCase)
    }

    @Provides
    fun providesGetJokeUseCase(jokeRepository: IJokeRepository) : GetJokeUseCase {
        return GetJokeUseCase(jokeRepository)
    }

    @Provides
    fun providesCategoriesRepository(jokeRemoteData: IJokeRemoteData) : IJokeRepository {
        return JokeRepository(jokeRemoteData)
    }

    @Provides
    fun provideJokeRemoteData(retrofit : Retrofit) : IJokeRemoteData {
        return JokeRemoteData(retrofit.create(IJokeApiClient::class.java))
    }
}