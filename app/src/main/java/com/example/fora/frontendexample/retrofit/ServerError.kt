package com.example.fora.frontendexample.retrofit

import com.google.gson.annotations.SerializedName

data class ServerError(@SerializedName("code") val code: Int? = 0, @SerializedName("message") val message: String = "")