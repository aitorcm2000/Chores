package com.aitor.chores.communication.users

import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.users.UserInputObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsersQueries (db: FirebaseFirestore) {

    private val usersTable : CollectionReference = db.collection(TableReferenceNames.USERS)

    suspend fun getUserById (id : String)
    : Any
    {
        val snap = usersTable.document(id).get().await()

        if (snap.exists() && snap.data != null) {
            return userFromSnapshot(snap)
        } else {
            return Any()
        }
    }

    suspend fun getUserByUsername (username : String)
    : Any
    {
        val snap = usersTable.whereEqualTo(UsersReferenceNames.USERNAME,username).get().await()

        if (!snap.isEmpty && snap.documents.isNotEmpty()) {
            return userFromSnapshot(snap.documents[0])
        } else {
            return Any()
        }
    }

    suspend fun getUserByMail (mail : String)
    : Any
    {
        val snap = usersTable.whereEqualTo(UsersReferenceNames.MAIL, mail).get().await()

        if (!snap.isEmpty && snap.documents.isNotEmpty()) {
            return userFromSnapshot(snap.documents[0])
        } else {
            return Any()
        }
    }

    private fun userFromSnapshot (snap : DocumentSnapshot) : UserInputObject{

        val username = snap.get(UsersReferenceNames.USERNAME).toString()
        val password = snap.get(UsersReferenceNames.PASSWORD).toString()
        val mail = snap.get(UsersReferenceNames.MAIL).toString()
        val groups = snap.get(UsersReferenceNames.GROUPS) as List<String>
        val choresCompleted = snap.get(UsersReferenceNames.CHORES_COMPLETED) as List<HashMap<String, Any>>

        return UserInputObject(snap.id,username,password,mail,groups,choresCompleted)
    }
}