package com.aitor.chores.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aitor.chores.R
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.databinding.ActivityRegisterBinding
import com.aitor.chores.model.users.UserOutputObject
import com.aitor.chores.view.controllers.register.RegisterController
import com.aitor.chores.view.controllers.register.RegisterSetup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private val viewModel : RegisterController by viewModels()
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var setup: RegisterSetup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.registerbutton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val username = binding.username.text.toString()
                val mail = binding.mail.text.toString()
                val password = binding.password.text.toString()

                val validate = viewModel.validateInput(username, mail, password)

                if (validate) {
                    val user = UserOutputObject(
                        username, password, mail,
                        listOf(), listOf(hashMapOf())
                    )
                    viewModel.addUser(user)
                }
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                startActivity(intent)
            }
        }
        setup = RegisterSetup(this, this, binding)

    }
}