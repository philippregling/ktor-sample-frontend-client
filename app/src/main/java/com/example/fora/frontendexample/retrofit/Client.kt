package com.example.fora.frontendexample.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object Client {


    val testUrl = "http://83.169.34.18:6060/api/"

    fun get(): Retrofit {
        var apiUrl = testUrl
        if (!apiUrl.endsWith("/")) {
            apiUrl += "$apiUrl/"
        }
        val loggingInterceptor = HttpLoggingInterceptor {
            Timber.tag("Requester").d(it)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        return Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}