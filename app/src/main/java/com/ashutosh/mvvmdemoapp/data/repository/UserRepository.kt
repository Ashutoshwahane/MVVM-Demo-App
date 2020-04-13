package com.ashutosh.mvvmdemoapp.data.repository


import com.ashutosh.mvvmdemoapp.data.network.MyApi
import com.ashutosh.mvvmdemoapp.data.network.SafeApiRequest
import com.ashutosh.mvvmdemoapp.data.network.responses.AuthResponse
import com.ashutosh.mvvmdemoapp.db.AppDatabase
import com.ashutosh.mvvmdemoapp.db.entities.User

import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {
   suspend fun userLogin(email : String, password: String): AuthResponse{

       return apiRequest{ api.userLogin(email, password)}
    }

    suspend fun saveUser(user: User) = db.userDao().upsert(user)

    fun getUser() = db.userDao().getuser()
}