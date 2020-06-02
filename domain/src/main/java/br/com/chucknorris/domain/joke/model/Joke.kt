package br.com.domain.joke.model

data class Joke (
    val id : String = "",
    val categories : List<String> = emptyList(),
    val iconUrl : String,
    val value : String = "",
    val url : String = ""
)