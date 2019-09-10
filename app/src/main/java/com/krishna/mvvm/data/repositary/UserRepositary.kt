package com.krishna.mvvm.data.repositary

import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.SafeApiRequest
import com.krishna.mvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepositary : SafeApiRequest(){

    suspend fun userLogin(email:String,password:String):AuthResponse{
        return apiRequest{ MyApi().userLogin(email,password)}
    }


}