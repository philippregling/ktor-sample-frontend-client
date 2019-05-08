package com.example.fora.frontendexample.model

import com.google.gson.annotations.SerializedName

data class AuthUser(@SerializedName("Jwt-Token") val token: String? = null,
                    @SerializedName("user") val user: User? = null)