package com.aitor.chores.communication

import com.aitor.chores.communication.chores.ChoresReferenceNames
import com.aitor.chores.model.chores.ChoreObject
import com.aitor.chores.model.users.UserObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class InnerJoins{
    val db : FirebaseFirestore
    constructor(db: FirebaseFirestore){
        this.db = db
    }

    suspend fun getAllChoresForUser (user : UserObject) {
        val choresTable = db.collection(TableReferenceNames.CHORES)
        val userGroupTable = db.collection(TableReferenceNames.USER_GROUP)

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