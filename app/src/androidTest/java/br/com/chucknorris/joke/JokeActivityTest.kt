package br.com.chucknorris.joke

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.chucknorris.OkHttpIdlingResourceRule
import br.com.chucknorris.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class JokeActivityTest {

    @get:Rule
    var rule = OkHttpIdlingResourceRule()

    @get:Rule
    private val activityRule = ActivityTestRule(
        JokeActivity::class.java, false, true)

    @Before
    fun setup() {
        Intents.init()
        activityRule.launchActivity(getIntentWithExtras())
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun launchAndCheckVisibility() {
        onView(withId(R.id.joke_img)).check(matches(isDisplayed()))
        onView(withId(R.id.joke_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.go_to_page_btn)).check(matches(isDisplayed()))
    }

    private fun getIntentWithExtras() : Intent {
        val targetContext: Context = ApplicationProvider.getApplicationContext()
        return Intent(targetContext, JokeActivity::class.java).apply {
            putExtra("categoryName", "science")
        }
    }
}