package com.krishna.mvvm.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.krishna.mvvm.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val navContoller = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupWithNavController(nav_view,navContoller)
        NavigationUI.setupActionBarWithNavController(this,navContoller,drawer_layout)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this,R.id.fragment), drawer_layout
        )
    }
}
