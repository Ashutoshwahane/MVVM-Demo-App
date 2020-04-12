package com.ashutosh.mvvmdemoapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.ashutosh.mvvmdemoapp.data.repository.UserRepository

class AuthViewModel : ViewModel(){
    var email : String? = null
    var password : String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if (email.isNullOrEmpty()  || password.isNullOrEmpty()){
            // failure
            authListener?.onFailure("Invalid email or Password")

            return
        }
        // Success
        val loginResponse = UserRepository().userLogin(email!!,password!!)
        authListener?.onSuccess(loginResponse)
    }
}