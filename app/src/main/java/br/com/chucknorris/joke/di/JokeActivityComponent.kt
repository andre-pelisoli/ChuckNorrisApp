package br.com.chucknorris.joke.di

import br.com.chucknorris.base.rx.di.SchedulerProviderComponent
import br.com.chucknorris.category.di.CategoryScope
import br.com.chucknorris.joke.JokeActivity
import br.com.chucknorris.network.di.ApiServiceComponent
import dagger.Component

@CategoryScope
@Component(
    modules = [JokeModule::class],
    dependencies = [ApiServiceComponent::class, SchedulerProviderComponent::class])
interface JokeActivityComponent {
    fun inject(jokeActivity : JokeActivity)
}