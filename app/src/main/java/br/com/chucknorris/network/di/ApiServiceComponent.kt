package br.com.chucknorris.network.di

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiServiceModule::class])
interface ApiServiceComponent {
    fun getRetrofit() : Retrofit
}