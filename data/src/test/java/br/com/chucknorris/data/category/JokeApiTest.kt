package br.com.data.category

import br.com.data.joke.model.JokeApi
import org.junit.Test

class JokeApiTest {
    val jsonApiStub = JokeApi(
        "123",
        listOf("science"),
        "2020-01-05 13:42:23.484083",
        "2020-01-05 13:42:23.484083",
        "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
        "https://api.chucknorris.io/jokes/qTcPNQbLTxmaOMPfYKj9iQ",
        "The Pope calls Chuck Norris \"Father.\""
    )

    @Test
    fun `Mapper JokerApi to Joker`() {
        jsonApiStub.toJoke().apply {
            assert(id.contentEquals("123"))
            assert(categories.size == 1)
            assert(categories[0].contentEquals("science"))
            assert(iconUrl.contentEquals("https://assets.chucknorris.host/img/avatar/chuck-norris.png"))
            assert(url.contentEquals("https://api.chucknorris.io/jokes/qTcPNQbLTxmaOMPfYKj9iQ"))
            assert(value.contentEquals("The Pope calls Chuck Norris \"Father.\""))
        }
    }

}