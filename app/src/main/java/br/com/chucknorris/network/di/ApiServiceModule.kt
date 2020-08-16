package br.com.chucknorris.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(jsonConverterFactory: MoshiConverterFactory, httpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverterFactory)
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideJsonConverter() : MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build())

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient = HttpClientFactory.client

    companion object {
        const val baseUrl = "https://api.chucknorris.io"
    }
}