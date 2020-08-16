package br.com.chucknorris.domain.joke

import br.com.chucknorris.domain.joke.model.Joke

interface IJokeRepository {

    suspend fun getJoke(category : String) : Joke

}