package com.aitor.chores.view.controllers.register

import androidx.lifecycle.ViewModel
import com.aitor.chores.communication.users.UsersPUD
import com.aitor.chores.communication.users.UsersQueries
import com.aitor.chores.model.users.UserInputObject
import com.aitor.chores.model.users.UserOutputObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class RegisterController : ViewModel() {

    suspend fun validateInput(
        user: String,
        email: String,
        password: String,
    ): Boolean {

        val userExist = UsersQueries().getUserByUsername(user)
        val mailExist = UsersQueries().getUserByMail(user)


        if (email.isEmpty() || password.isEmpty() || user.isEmpty()) {
            return false
        }

        if (userExist.username != "" || mailExist.email != "") {
            return false
        }

        return true
    }

    suspend fun addUser(user : UserOutputObject){
        UsersPUD().addUser(user)
    }
}