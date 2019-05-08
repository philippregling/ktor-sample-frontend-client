package com.example.fora.frontendexample.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface TestAPI {

    @GET("stations.json")
    fun getTestRequest(): Call<List<Unit>>
}