package com.example.fora.frontendexample.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("user_id") val id: Int,
                @SerializedName("user_name") val userName: String)