package com.aitor.chores.communication.chores

import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.chores.ChoreGroupObject
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await

class ChoresGroupsQueries(){

    private val choreGroupTable: CollectionReference =
        FirestoreConnection.db!!.collection(TableReferenceNames.CHORES_GROUP)

    suspend fun getAllChoreGroups () :  List<ChoreGroupObject>{

        val choreGroups :  List<ChoreGroupObject>
        val snap = choreGroupTable.get().await()

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
            return listOf<ChoreGroupObject>()
        }
    }
}