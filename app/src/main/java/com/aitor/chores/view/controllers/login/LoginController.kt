package com.aitor.chores.view.controllers.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.aitor.chores.communication.chores.ChoresQueries
import com.aitor.chores.communication.users.UsersQueries
import com.aitor.chores.databinding.ActivityLoginBinding
import com.aitor.chores.model.users.UserInputObject
import com.aitor.chores.view.activities.MainActivity
import com.aitor.chores.view.activities.RegisterActivity
import com.aitor.chores.view.controllers.CommonData
import com.google.firebase.firestore.FirebaseFirestore

class LoginController(
    private val activity: AppCompatActivity,
    private val context: Context,
    private val binding: ActivityLoginBinding
) {

    suspend fun userValidation(): Boolean {

        val name = binding.username.text.toString()
        val password = binding.password.text.toString()

        var user: UserInputObject


        if (isItMailOrUsername(name)) {

            val response = UsersQueries().getUserByMail(name)

            if (response is UserInputObject) {
                user = response
            } else {
                user = UserInputObject()
            }

        } else {

            val response = UsersQueries().getUserByUsername(name)

            if (response is UserInputObject) {
                user = response
            } else {
                user = UserInputObject()
            }

        }

        if (user != UserInputObject()) {
            val bool = password == user.password
            if(bool){
                CommonData.user = user
                CommonData.userChores = ChoresQueries().getAllChoresForUser(user)
            }
            return bool
        }

        return false
    }


    fun gotoRegister() {
        val intent = Intent(context, RegisterActivity::class.java)
        activity.startActivity(intent)
    }

    fun gotoMain() {
        val intent = Intent(context, MainActivity::class.java)
        activity.startActivity(intent)
    }

    private fun isItMailOrUsername(credential: String): Boolean {
        return credential.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex())
    }

}