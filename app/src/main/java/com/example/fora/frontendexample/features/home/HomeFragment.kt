package com.example.fora.frontendexample.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.fora.frontendexample.R
import com.example.fora.frontendexample.model.Message
import com.example.fora.frontendexample.retrofit.ServerError
import com.example.fora.frontendexample.util.buildErrorToolbar
import com.example.fora.frontendexample.util.buildProgressbar
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        checkTokenValidity()
    }

    fun checkTokenValidity() {
        val progressBar = buildProgressbar(activity)
        progressBar?.show()
        val validityObserver = Observer<Any> {
            progressBar?.dismiss()
            if (it is Message) {
                Timber.d(it.toString())
                home_token?.text = it.toString()
            } else if (it is ServerError) {
                buildErrorToolbar(activity, it)?.show()
            }
        }
        viewModel.checkTokenValidity().observe(this@HomeFragment, validityObserver)
    }


}