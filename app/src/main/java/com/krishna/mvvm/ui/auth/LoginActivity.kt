package com.krishna.mvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.krishna.mvvm.R
import com.krishna.mvvm.data.db.AppDatabase
import com.krishna.mvvm.data.db.entities.User
import com.krishna.mvvm.data.network.MyApi
import com.krishna.mvvm.data.network.NetworkConnectionInterceptor
import com.krishna.mvvm.data.repositary.UserRepositary
import com.krishna.mvvm.databinding.ActivityLoginBinding
import com.krishna.mvvm.ui.home.HomeActivity
import com.krishna.mvvm.util.hide
import com.krishna.mvvm.util.show
import com.krishna.mvvm.util.snackbar
import com.krishna.mvvm.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , AuthListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionIntercepter = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionIntercepter)
        val db = AppDatabase(this)
        val repositary = UserRepositary(api,db)

        val factory = AuthViewModelFactory(repositary)


        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {  user ->
            if(user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
