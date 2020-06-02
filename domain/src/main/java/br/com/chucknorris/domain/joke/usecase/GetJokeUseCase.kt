package br.com.domain.joke.usecase

import br.com.domain.joke.IJokeRepository
import br.com.domain.joke.model.Joke
import io.reactivex.rxjava3.core.Single

class GetJokeUseCase (private val repository: IJokeRepository) {
    fun getJoke (category : String) : Single<Joke> {
        return if(category.isBlank()) Single.error(Exception("Category name cannot be null"))
        else repository.getJoke(category)
    }
}