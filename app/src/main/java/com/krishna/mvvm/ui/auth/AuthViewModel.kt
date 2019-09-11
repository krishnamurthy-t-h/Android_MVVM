package com.krishna.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.krishna.mvvm.data.repositary.UserRepositary
import com.krishna.mvvm.util.ApiException
import com.krishna.mvvm.util.Coroutines

class AuthViewModel(
    private val repositary: UserRepositary
): ViewModel(){

    var email:String?=null
    var password:String?=null

    var authListener:AuthListener?=null

    fun getLoggedInUser() = repositary.getUser()


    fun onLoginButtonClick(view: View){
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            //Failure show error
            authListener?.onFailure("Invalid email and password")
            return
        }
        Coroutines.main{

            try {
                val authResponse = repositary.userLogin(email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repositary.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }


        }

    }
}