package com.aitor.chores.communication

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

object FirestoreConnection {
    var db : FirebaseFirestore?
        get() = Firebase.firestore
        set(value) {
            db = value
        }
}