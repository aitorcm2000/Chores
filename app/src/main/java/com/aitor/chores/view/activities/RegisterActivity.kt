package com.aitor.chores.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aitor.chores.R
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.databinding.ActivityLoginBinding
import com.aitor.chores.databinding.ActivityRegisterBinding
import com.aitor.chores.view.controllers.login.LoginSetup
import com.aitor.chores.view.controllers.register.RegisterSetup
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var setup: RegisterSetup
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirestoreConnection().startConnection()
        setup = RegisterSetup(this, this, binding, db)
//        setup.basicSetup()
//        setup.buttonSetup()

    }
}