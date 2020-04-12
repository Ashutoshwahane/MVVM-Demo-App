package com.ashutosh.mvvmdemoapp.ui.auth

import androidx.lifecycle.LiveData
import com.ashutosh.mvvmdemoapp.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}