package com.aitor.chores.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.aitor.chores.R
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.databinding.ActivityLoginBinding
import com.aitor.chores.view.controllers.login.LoginSetup
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var setup: LoginSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            val username = intent.extras!!.getString("username")
            val password = intent.extras!!.getString("password")

            if (username != null && password != null) {
                binding.username.setText(username)
                binding.password.setText(password)
            }
        } else {
            binding.username.setText("")
            binding.password.setText("")
        }

        setup = LoginSetup(this, this, binding)
        setup.basicSetup()
        setup.buttonSetup()

    }


}