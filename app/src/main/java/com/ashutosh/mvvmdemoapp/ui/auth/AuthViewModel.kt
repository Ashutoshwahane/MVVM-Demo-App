package com.ashutosh.mvvmdemoapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.ashutosh.mvvmdemoapp.data.repository.UserRepository
import com.ashutosh.mvvmdemoapp.utils.ApiException
import com.ashutosh.mvvmdemoapp.utils.Coroutines
import com.ashutosh.mvvmdemoapp.utils.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel(){
    var email : String? = null
    var password : String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if (email.isNullOrEmpty()  || password.isNullOrEmpty()){
            // failure
            authListener?.onFailure("Invalid email or Password")

            return
        }

        Coroutines.main {


            try {
                val authResponse = repository.userLogin(email!!,password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(authResponse.user)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }
            catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}