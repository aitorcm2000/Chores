package com.aitor.chores.communication

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FirestoreConnection {
    fun startConnection () : FirebaseFirestore {
        return Firebase.firestore
    }
}