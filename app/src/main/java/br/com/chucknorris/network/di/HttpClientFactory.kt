package br.com.chucknorris.network.di

import okhttp3.OkHttpClient

object HttpClientFactory {

    val client : OkHttpClient by lazy {  return@lazy OkHttpClient.Builder().build() }
}