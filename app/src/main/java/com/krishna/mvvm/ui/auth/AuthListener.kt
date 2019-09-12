package com.krishna.mvvm.ui.auth

import com.krishna.mvvm.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user:User)

    fun onFailure(message:String)
}