package com.aitor.chores.communication.chores

import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.communication.users.UsersReferenceNames
import com.aitor.chores.model.chores.ChoreObject
import com.aitor.chores.model.users.UserObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ChoresQueries(db: FirebaseFirestore) {

    private val choresTable : CollectionReference = db.collection(TableReferenceNames.CHORES)

    suspend fun getChoreById (id : String) : Any {
        val snap = choresTable.document(id).get().await()

        if (snap.exists() && snap.data != null) {

            val createdBy = snap.get(ChoresReferenceNames.CREATEDBY).toString()
            val group = snap.get(ChoresReferenceNames.GROUP).toString()
            val name = snap.get(ChoresReferenceNames.NAME).toString()

            val chore = ChoreObject(snap.id, createdBy, group, name)

            return chore

        } else {
            return Any()
        }
    }

    suspend fun getAllChoresForUser (user : UserObject) {

        val chores = mutableListOf<ChoreObject>()

        val byDefault = choresTable.whereEqualTo(ChoresReferenceNames.CREATEDBY, "default")
            .get().await()

        val byUser = choresTable.whereEqualTo(ChoresReferenceNames.CREATEDBY, user.id)
            .get().await()

        for (group in user.groups){
            val byGroup = choresTable.whereEqualTo(ChoresReferenceNames.CREATEDBY, group)
                .get().await()

            if (!byGroup.isEmpty && !byGroup.documents.isEmpty()){
                chores.addAll(listRetreiver(byGroup.documents))
            }
        }

        if (!byDefault.isEmpty && !byDefault.documents.isEmpty()){
            chores.addAll(listRetreiver(byDefault.documents))
        }

        if (!byUser.isEmpty && !byUser.documents.isEmpty()){
            chores.addAll(listRetreiver(byUser.documents))
        }

        println(chores)
    }

    private fun listRetreiver (snap: List<DocumentSnapshot>) : List<ChoreObject>{
        val list = mutableListOf<ChoreObject>()
        for (doc in snap){
            val added = choreFromDocumentSnapshot(doc)
            list.add(added)
        }
        return list
    }

    private fun choreFromDocumentSnapshot(doc : DocumentSnapshot) : ChoreObject{
        val name = doc.get(ChoresReferenceNames.NAME).toString()
        val createdBy = doc.get(ChoresReferenceNames.CREATEDBY).toString()
        val group = doc.get(ChoresReferenceNames.GROUP).toString()

        return ChoreObject(doc.id,createdBy,group,name)
    }

}