package com.example.fora.frontendexample.features.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fora.frontendexample.retrofit.repositories.AuthRepository

class RegisterViewModel : ViewModel() {

    private val registerLiveData = MutableLiveData<Any>()


    fun register(userName: String?, password: String?): MutableLiveData<Any> {
        //TODO Plausibility checks
        return AuthRepository.registerUser(userName.toString(), password.toString(), registerLiveData)
    }

}