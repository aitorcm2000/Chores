package com.aitor.chores.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aitor.chores.R
import com.aitor.chores.view.login.LoginFragment

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}