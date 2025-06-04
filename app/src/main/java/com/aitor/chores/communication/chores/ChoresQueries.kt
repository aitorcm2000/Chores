package com.aitor.chores.communication.chores

import com.aitor.chores.communication.users.UsersReferenceNames
import com.aitor.chores.model.chores.ChoreObject
import com.aitor.chores.model.users.UserObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await


class ChoresQueries(val collection : CollectionReference){

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

//    fun documentSnapShotToChoreObject (data : DocumentSnapshot) : ChoreObject{
//        return ChoreObject(
//            data.id,
//            data.get("createdBy").toString(),
//            data.get)
//    }
}