package com.krishna.mvvm.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krishna.mvvm.data.repositary.UserRepositary

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repositary: UserRepositary): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  AuthViewModel(repositary) as T
    }

}