package com.example.fora.frontendexample.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fora.frontendexample.retrofit.repositories.AuthRepository

class HomeViewModel : ViewModel() {


    val tokenLiveData = MutableLiveData<Any>()
    val currentUser = AuthRepository.currentUser


    fun checkTokenValidity(): MutableLiveData<Any> {
        return AuthRepository.checkTokenValidity(currentUser?.token.toString(), tokenLiveData)
    }


}