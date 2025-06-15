package com.aitor.chores.view.controllers

import com.aitor.chores.communication.chores.ChoresQueries
import com.aitor.chores.model.chores.ChoreInputObject
import com.aitor.chores.model.users.UserInputObject
import com.aitor.chores.model.users_groups.UserGroupInputObject

object CommonData {
    var user: UserInputObject = UserInputObject()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var groups = mutableListOf<UserGroupInputObject>()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var userChores = mutableListOf<ChoreInputObject>()
        get() {
            return field
        }
        set(value) {
            field = value
        }

    fun choresForUserByType(choreGroupId: String): List<ChoreInputObject> {
        return userChores.filter { it.group == choreGroupId }
    }

    suspend fun setUserChores() {
        userChores.clear()
        val chores = ChoresQueries().getAllChoresForUser(user)
        userChores.addAll(chores)
    }
}