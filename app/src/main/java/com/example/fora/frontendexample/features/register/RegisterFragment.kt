package com.example.fora.frontendexample.features.register

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
import kotlinx.android.synthetic.main.fragment_register.*
import timber.log.Timber

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_username?.setText("test")
        register_password?.setText("123456")

        register_start_register?.setOnClickListener {
            //TODO check password repeat
            startRegister(register_username?.text?.toString(), register_password?.text?.toString())
        }
    }

    fun startRegister(userName: String?, password: String?) {
        val progressBar = buildProgressbar(activity)
        progressBar?.show()
        val registerObserver = Observer<Any> {
            progressBar?.dismiss()
            if (it is AuthUser) {
                Timber.d(it.toString())
                view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.register_to_home) }
                        ?: Unit
            } else if (it is ServerError) {
                buildErrorToolbar(activity, it)?.show()
            }

        }
        viewModel.register(userName, password).observe(this@RegisterFragment, registerObserver)
    }

}