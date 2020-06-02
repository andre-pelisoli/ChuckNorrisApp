package br.com.data.joke.model

import br.com.domain.joke.model.Joke
import com.squareup.moshi.Json

class JokeApi (
    val id : String?,
    val categories : List<String> = emptyList(),
    @Json(name = "created_at")
    val createdAt: String? ,
    @Json(name = "updated_at")
    val updateAt : String?,
    @Json(name = "icon_url")
    val iconUrl: String?,
    val url: String?,
    val value : String?
) {

    fun toJoke () : Joke {
        return Joke(id ?: "" , categories, iconUrl ?: "", value ?: "", url ?: "")
    }

}