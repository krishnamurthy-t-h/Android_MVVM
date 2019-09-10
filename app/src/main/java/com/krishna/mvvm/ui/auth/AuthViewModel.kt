package com.krishna.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.krishna.mvvm.data.repositary.UserRepositary
import com.krishna.mvvm.util.ApiException
import com.krishna.mvvm.util.Coroutines

class AuthViewModel: ViewModel(){

    var email:String?=null
    var password:String?=null

    var authListener:AuthListener?=null


    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            //Failure show error
            authListener?.onFailure("Invalid email and password")
            return
        }
        Coroutines.main{

            try {
                val authResponse = UserRepositary().userLogin(email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }


        }

    }
}