package com.krishna.mvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krishna.mvvm.data.repositary.UserRepositary

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val repositary: UserRepositary): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ProfileViewModel(repositary) as T
    }

}