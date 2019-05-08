package com.example.fora.frontendexample.retrofit.repositories

import androidx.lifecycle.MutableLiveData
import com.example.fora.frontendexample.model.AuthUser
import com.example.fora.frontendexample.model.Message
import com.example.fora.frontendexample.retrofit.Client
import com.example.fora.frontendexample.retrofit.IAuth
import com.example.fora.frontendexample.retrofit.ServerError
import com.example.fora.frontendexample.util.buildAuthToken
import com.example.fora.frontendexample.util.handleError
import com.example.fora.frontendexample.util.responseIsOkay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AuthRepository {

    var currentUser: AuthUser? = null

    fun registerUser(userName: String, password: String, liveData: MutableLiveData<Any>): MutableLiveData<Any> {
        Client.get().create(IAuth::class.java)
                .register(userName, password)
                .enqueue(object : Callback<AuthUser> {

                    override fun onResponse(call: Call<AuthUser>, response: Response<AuthUser>) {
                        if (responseIsOkay(response)) {
                            currentUser = response.body()
                            liveData.value = response.body()
                        } else {
                            handleError(response.code(), response.errorBody()?.string().toString(), liveData)
                        }
                    }

                    override fun onFailure(call: Call<AuthUser>, t: Throwable) {
                        liveData.value = ServerError(message = t.message.toString())
                    }
                })
        return liveData
    }

    fun loginUser(userName: String, password: String, liveData: MutableLiveData<Any>): MutableLiveData<Any> {
        Client.get().create(IAuth::class.java)
                .login(userName, password)
                .enqueue(object : Callback<AuthUser> {

                    override fun onResponse(call: Call<AuthUser>, response: Response<AuthUser>) {
                        if (responseIsOkay(response)) {
                            currentUser = response.body()
                            liveData.value = response.body()
                        } else {
                            handleError(response.code(), response.errorBody()?.string().toString(), liveData)
                        }
                    }

                    override fun onFailure(call: Call<AuthUser>, t: Throwable) {
                        liveData.value = ServerError(message = t.message.toString())
                    }
                })
        return liveData
    }

    fun checkTokenValidity(token: String?, liveData: MutableLiveData<Any>): MutableLiveData<Any> {
        Client.get().create(IAuth::class.java)
                .checkTokenValidity(buildAuthToken(token))
                .enqueue(object : Callback<Message?> {

                    override fun onResponse(call: Call<Message?>, response: Response<Message?>) {
                        if (responseIsOkay(response)) {
                            liveData.value = response.body()
                        } else {
                            handleError(response.code(), response.errorBody()?.string().toString(), liveData)
                        }
                    }

                    override fun onFailure(call: Call<Message?>, t: Throwable) {
                        liveData.value = ServerError(message = t.message.toString())
                    }
                })
        return liveData
    }

}