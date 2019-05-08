package com.example.fora.frontendexample.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fora.frontendexample.retrofit.repositories.AuthRepository

class LoginViewModel : ViewModel() {

    private val loginLiveData = MutableLiveData<Any>()

    fun login(userName: String?, password: String?): MutableLiveData<Any> {
        //TODO Plausibility checks
        return AuthRepository.loginUser(userName.toString(), password.toString(), loginLiveData)
    }

}