package com.krishna.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

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
        //Success
        authListener?.onSuccess()
    }
}