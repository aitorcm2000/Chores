package com.aitor.chores.communication.chores

import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.chores.ChoreInputObject
import com.aitor.chores.model.chores.ChoreOutputObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ChoresPUD(db: FirebaseFirestore) {

    private val choresTable : CollectionReference = db.collection(TableReferenceNames.CHORES)

    suspend fun addChore (chore : ChoreOutputObject){

        val ref = choresTable.add(chore).await()
    }

    suspend fun updateChore (chore : ChoreInputObject) {

        val choreOutput = ChoreOutputObject(chore.createdBy,chore.group,chore.name)

        val ref = choresTable.document(chore.id).set(choreOutput).await()
    }

}