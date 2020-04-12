package com.ashutosh.mvvmdemoapp.data.network.responses

import com.ashutosh.mvvmdemoapp.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?

)