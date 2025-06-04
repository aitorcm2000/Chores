package com.aitor.chores.communication.users

import com.aitor.chores.model.users.UserObject
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class UsersQueries (val collection : CollectionReference){

    suspend fun getUserById (id : String) : Any {
        val snap = collection.document(id).get().await()

        if (snap.exists() && snap.data != null) {
             val user = UserObject(
                snap.id.toString(),
                snap.get(UsersReferenceNames.USERNAME).toString(),
                snap.get(UsersReferenceNames.PASSWORD).toString(),
                snap.get(UsersReferenceNames.MAIL).toString(),
                snap.get(UsersReferenceNames.GROUPS) as List<String>,
                snap.get(UsersReferenceNames.CHORES_COMPLETED) as List<Any>
            )

            return user
            println(user)

        } else {
            return Any()
        }
    }

//    private fun processUser (user : UserObject) : UserObject{
//        var processedUser = null
//
//
//        return processUser
//    }
}