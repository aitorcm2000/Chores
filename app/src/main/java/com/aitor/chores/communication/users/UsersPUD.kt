package com.aitor.chores.communication.users

import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.users.UserInputObject
import com.aitor.chores.model.users.UserOutputObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsersPUD() {

    private val userTable : CollectionReference = FirestoreConnection.db!!.collection(TableReferenceNames.USERS)

    suspend fun addUser (user : UserOutputObject){

        val ref = userTable.add(user).await()
    }

    suspend fun updateChore (user : UserInputObject) {

        val userOutput = UserOutputObject(
            user.username, user.password, user.email, user.groups, user.choresCompleted
        )

        val ref = userTable.document(user.id).set(userOutput).await()
    }
}