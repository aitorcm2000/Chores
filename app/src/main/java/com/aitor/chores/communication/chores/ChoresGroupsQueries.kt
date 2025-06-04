package com.aitor.chores.communication.chores

import com.aitor.chores.communication.users.UsersReferenceNames
import com.aitor.chores.model.chores.ChoreGroupObject
import com.aitor.chores.model.users.UserObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.tasks.await

class ChoresGroupsQueries(val collection : CollectionReference){

    suspend fun getAllChoreGroups () : Any{

        val choreGroups :  List<ChoreGroupObject>
        val snap = collection.get().await()

        if (!snap.documents.isEmpty()) {

            val docs = snap.documents
            val groups = mutableListOf<ChoreGroupObject>()

            for (doc in docs) {
                val name = doc.get(ChoresReferenceNames.NAME)
                val chores = doc.get(ChoresReferenceNames.CHORES) as List<String>

                val group = ChoreGroupObject(
                    doc.id.toString(),name.toString(), chores
                )
                groups.add(group)
            }

            choreGroups = groups as List<ChoreGroupObject>
            return choreGroups

        } else {
            println("DAMN $snap")
            return Any()
        }
    }
}