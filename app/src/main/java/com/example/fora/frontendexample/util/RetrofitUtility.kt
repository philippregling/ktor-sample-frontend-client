package com.example.fora.frontendexample.util

import androidx.lifecycle.MutableLiveData
import com.example.fora.frontendexample.retrofit.ServerError
import com.google.gson.Gson
import retrofit2.Response

fun responseIsOkay(response: Response<*>): Boolean {
    return response.isSuccessful && response.body() != null
}

fun handleError(errorCode: Int? = 0, errorBody: String, liveData: MutableLiveData<Any>) {
    val serverError = Gson().fromJson<ServerError>(errorBody, ServerError::class.java)
    if (serverError != null) {
        liveData.value = serverError
    } else {
        liveData.value = ServerError(errorCode, errorBody)
    }
}

fun buildAuthToken(token: String?): String {
    return "Bearer $token"
}

