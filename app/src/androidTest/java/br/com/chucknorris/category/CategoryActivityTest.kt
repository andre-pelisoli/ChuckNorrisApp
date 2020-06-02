package br.com.chucknorris.category

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.chucknorris.OkHttpIdlingResourceRule
import br.com.chucknorris.R
import br.com.chucknorris.category.adapter.CategoryViewHolder
import br.com.chucknorris.joke.JokeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CategoryActivityTest {

    @get:Rule
    var rule = OkHttpIdlingResourceRule()

    @get:Rule
    private val activityRule = ActivityTestRule(
        CategoryActivity::class.java, false, true)

    @Before
    fun setup() {
        Intents.init()
        activityRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun launchAndCheckVisibility() {
        val headerText = onView(withId(R.id.header_txt))
        headerText.check(matches(isDisplayed()))

        val recyclerView = onView(withId(R.id.categories_rv))
        recyclerView.check(matches(isDisplayed()))
    }

    @Test
    fun openJokeActivity() {
        val recyclerView = onView(withId(R.id.categories_rv))
        recyclerView.perform(actionOnItemAtPosition<CategoryViewHolder>(1, click()))

        intended(hasComponent(JokeActivity::class.java.name))
    }
}