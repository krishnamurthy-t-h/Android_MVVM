package com.krishna.mvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import com.krishna.mvvm.data.repositary.UserRepositary

class ProfileViewModel(
    repositary: UserRepositary
) : ViewModel() {

    val user = repositary.getUser()

}
