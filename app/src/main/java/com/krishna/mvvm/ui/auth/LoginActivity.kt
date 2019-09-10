package com.krishna.mvvm.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.krishna.mvvm.R
import com.krishna.mvvm.data.db.entities.User
import com.krishna.mvvm.databinding.ActivityLoginBinding
import com.krishna.mvvm.util.hide
import com.krishna.mvvm.util.show
import com.krishna.mvvm.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , AuthListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        toast("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}
