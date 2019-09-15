package com.krishna.mvvm.data.repositary

import com.krishna.mvvm.data.db.AppDatabase
import com.krishna.mvvm.data.db.entities.User
import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.SafeApiRequest
import com.krishna.mvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepositary(
    private val api: MyApi,
    private val db:AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email:String,password:String):AuthResponse{
        return apiRequest{ api.userLogin(email,password)}
    }

    suspend fun userSignUp(name:String,email:String,password:String):AuthResponse{
        return apiRequest{ api.userSignUp(name, email, password)}
    }

    suspend fun saveUser(user: User) =  db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()



}