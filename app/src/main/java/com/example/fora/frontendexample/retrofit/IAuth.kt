package com.example.fora.frontendexample.retrofit

import com.example.fora.frontendexample.model.AuthUser
import com.example.fora.frontendexample.model.Message
import retrofit2.Call
import retrofit2.http.*

interface IAuth {

    @POST("auth/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String?, @Field("password") password: String?): Call<AuthUser>

    @POST("auth/register")
    @FormUrlEncoded
    fun register(@Field("username") username: String?, @Field("password") password: String?): Call<AuthUser>

    @GET("auth/auth")
    fun checkTokenValidity(@Header("Authorization") jwtToken: String?): Call<Message?>


}