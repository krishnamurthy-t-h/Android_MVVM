package com.krishna.mvvm.data.network.responses

import com.krishna.mvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)