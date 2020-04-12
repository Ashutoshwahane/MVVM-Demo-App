package com.ashutosh.mvvmdemoapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.ashutosh.mvvmdemoapp.data.repository.UserRepository
import com.ashutosh.mvvmdemoapp.utils.Coroutines

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

        Coroutines.main {

            val response = UserRepository().userLogin(email!!,password!!)
            if (response.isSuccessful){
                authListener?.onSuccess(response.body()?.user!!)
            }
            else{
                authListener?.onFailure("Error Code = ${response.code()}")
            }
        }
    }
}