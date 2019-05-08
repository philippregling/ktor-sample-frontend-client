package com.example.fora.frontendexample

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        fun sendTokenToServer(token: String) {
            //TODO send token here
        }
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        if (!token.isNullOrBlank()) {
            sendTokenToServer(token.toString())
        }
    }


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Timber.tag("1818").d("RECEIVED MESSAGE")
    }

}