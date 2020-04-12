package com.ashutosh.mvvmdemoapp.data.repository


import com.ashutosh.mvvmdemoapp.data.network.MyApi
import com.ashutosh.mvvmdemoapp.data.network.SafeApiRequest
import com.ashutosh.mvvmdemoapp.data.network.responses.AuthResponse

import retrofit2.Response

class UserRepository: SafeApiRequest() {
   suspend fun userLogin(email : String, password: String): AuthResponse{

       return apiRequest{ MyApi().userLogin(email, password)}
    }
}