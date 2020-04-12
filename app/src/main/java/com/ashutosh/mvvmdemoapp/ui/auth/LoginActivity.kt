package com.ashutosh.mvvmdemoapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashutosh.mvvmdemoapp.R
import com.ashutosh.mvvmdemoapp.databinding.ActivityLoginBinding
import com.ashutosh.mvvmdemoapp.db.entities.User
import com.ashutosh.mvvmdemoapp.utils.hide
import com.ashutosh.mvvmdemoapp.utils.show
import com.ashutosh.mvvmdemoapp.utils.snackbar
import com.ashutosh.mvvmdemoapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

    }

    override fun onStarted() {

        progress_bar.show()

    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
            root_layout.snackbar("${user.name} is Logged In")
        }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }

}

