package com.krishna.mvvm.data.repositary

import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepositary {

    suspend fun userLogin(email:String,password:String):Response<AuthResponse>{
        return  MyApi().userLogin(email,password)
    }


}