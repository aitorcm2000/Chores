package com.aitor.chores.communication.chores

import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.model.chores.ChoreObject
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ChoresPUD(db: FirebaseFirestore) {

    private val choresTable : CollectionReference = db.collection(TableReferenceNames.CHORES)

    suspend fun addChore (chore : ChoreObject){
        val ref = choresTable.add(chore).await()
    }
}