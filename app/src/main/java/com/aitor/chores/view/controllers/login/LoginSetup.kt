package com.aitor.chores.view.controllers.login

import android.content.Context
import android.view.Gravity
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityLoginBinding
import com.aitor.chores.view.components.ui.SnackBarFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginSetup (
    private val activity : AppCompatActivity,
    private val context : Context,
    private val binding: ActivityLoginBinding,
) {

    val controller = LoginController(activity, context, binding)

    fun basicSetup (){
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun buttonSetup(){
        binding.loginbutton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val anim = AnimationUtils.loadAnimation(context, R.anim.loading)

                binding.view.startAnimation(anim)

                if (controller.userValidation()){
                    binding.view.clearAnimation()

                    controller.gotoMain()
                } else {
                    binding.view.clearAnimation()
                    SnackBarFactory().createSnackBar(
                        binding.root,
                        activity.getString(R.string.login_error),
                        Snackbar.LENGTH_LONG,
                        Gravity.CENTER
                    )
                }
            }
        }

        binding.registerbutton.setOnClickListener {
            controller.gotoRegister()
        }
    }


}