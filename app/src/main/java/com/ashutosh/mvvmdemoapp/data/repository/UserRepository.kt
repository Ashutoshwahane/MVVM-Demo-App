package com.ashutosh.mvvmdemoapp.data.repository


import com.ashutosh.mvvmdemoapp.data.network.MyApi
import com.ashutosh.mvvmdemoapp.data.network.responses.AuthResponse

import retrofit2.Response

class UserRepository {
   suspend fun userLogin(email : String, password: String): Response<AuthResponse>{

       return MyApi().userLogin(email, password)
    }
}