package com.krishna.mvvm.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.krishna.mvvm.data.repositary.UserRepositary
import com.krishna.mvvm.util.ApiException
import com.krishna.mvvm.util.Coroutines
import com.krishna.mvvm.util.NoInternetException

class AuthViewModel(
    private val repositary: UserRepositary
): ViewModel(){

    var name:String?=null
    var email:String?=null
    var password:String?=null
    var passwordConfirm:String?=null
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
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }

    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View){
        authListener?.onStarted()
        if (name.isNullOrEmpty()){
            //Failure show error
            authListener?.onFailure("Name is Required!")
            return
        }
        if (email.isNullOrEmpty()){
            //Failure show error
            authListener?.onFailure("Invalid email or email required!")
            return
        }
        if (password.isNullOrEmpty() && password!=passwordConfirm){
            //Failure show error
            authListener?.onFailure("Invalid password")
            return
        }
        Coroutines.main{

            try {
                val authResponse = repositary.userSignUp(name!!,email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repositary.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }
}