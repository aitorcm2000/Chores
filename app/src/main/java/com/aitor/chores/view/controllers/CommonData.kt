package com.aitor.chores.view.controllers

import com.aitor.chores.communication.chores.ChoresQueries
import com.aitor.chores.communication.chores.ChoresReferenceNames
import com.aitor.chores.communication.users.UsersReferenceNames
import com.aitor.chores.model.chores.ChoreInputObject
import com.aitor.chores.model.chores.CompletedChoreObject
import com.aitor.chores.model.users.UserInputObject
import com.aitor.chores.model.users_groups.UserGroupInputObject
import com.google.firebase.Timestamp

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

    var lastChoreId = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var lastGroupId = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }


    fun completedChoresForUserByType(choreGroupId: String): List<CompletedChoreObject> {
        var result = emptyList<CompletedChoreObject>()

        if (groups.isEmpty()) {
            result = completedChoresForUser(choreGroupId)
        } else {
            completedChoresForUserByGroup(choreGroupId)
        }

        return result
    }

    fun completedChoresForUser(choreGroupId: String): List<CompletedChoreObject> {
        val chores = choresForUserByType(choreGroupId)
        val result = mutableListOf<CompletedChoreObject>()
        val completed = user.choresCompleted

        if (completed.isEmpty()) {
            return result
        }

        for (chore in chores) {
            if (chore.group == choreGroupId) {
                for (completedChore in completed) {
                    val filteredChore =
                        chores.filter { it.id == completedChore.get(ChoresReferenceNames.CHORES) }

                    if (filteredChore.isNotEmpty()){
                        val resultingchore = filteredChore.first()

                        val resultCompleted = CompletedChoreObject(
                            resultingchore,
                            completedChore.get(UsersReferenceNames.DONEWHEN) as Timestamp,
                            user.id
                        )

                        result.add(resultCompleted)
                    }
                }
            }
        }


        return result
    }


    fun completedChoresForUserByGroup(choreGroupId: String): List<CompletedChoreObject> {
        val chores = choresForUserByType(choreGroupId)
        val result = mutableListOf<CompletedChoreObject>()

        for (group in groups) {

            val completed = group.choresCompleted

            for (chore in completed) {
                if (chore.get(UsersReferenceNames.GROUPID) == choreGroupId) {
                    val filteredChore =
                        chores.filter { it.id == chore.get(UsersReferenceNames.CHORE) }
                    val resultingchore = filteredChore.first()

                    val resultCompleted = CompletedChoreObject(
                        resultingchore,
                        chore.get(UsersReferenceNames.DONEWHEN) as Timestamp,
                        chore.get(UsersReferenceNames.DONEBY) as String
                    )

                    result.add(resultCompleted)
                }
            }
        }

        return result
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
