package com.example.fora.frontendexample.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.fora.frontendexample.R
import com.example.fora.frontendexample.model.AuthUser
import com.example.fora.frontendexample.retrofit.ServerError
import com.example.fora.frontendexample.util.buildErrorToolbar
import com.example.fora.frontendexample.util.buildProgressbar
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber

class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_username?.setText("test")
        login_password?.setText("123456")
        login_start_login?.setOnClickListener {
            startLogin(login_username?.text?.toString(), login_password?.text?.toString())
        }
    }

    private fun startLogin(username: String?, password: String?) {
        val progressBar = buildProgressbar(activity)
        progressBar?.show()
        val registerObserver = Observer<Any> {
            progressBar?.dismiss()
            if (it is AuthUser) {
                Timber.d(it.toString())
                view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.login_to_home) }
                        ?: Unit
            } else if (it is ServerError) {
                buildErrorToolbar(activity, it)?.show()
            }

        }
        viewModel.login(username, password).observe(this@LoginFragment, registerObserver)
    }


}