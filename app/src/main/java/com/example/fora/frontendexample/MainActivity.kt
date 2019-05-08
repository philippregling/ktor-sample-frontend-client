package com.example.fora.frontendexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFCM()
    }

    fun initFCM() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Timber.tag("1818").d(it?.token.toString())
            //TODO
            if (!it?.token.isNullOrBlank()) {
                MyFirebaseMessagingService.sendTokenToServer(it?.token.toString())
            }
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.my_nav_host_fragment).navigateUp()

}
