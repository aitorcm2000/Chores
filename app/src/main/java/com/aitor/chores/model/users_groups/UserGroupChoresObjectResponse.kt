package com.aitor.chores.model.users_groups

import com.google.firebase.firestore.DocumentReference
import java.util.Date

data class UserGroupChoresObjectResponse (
    val id: String,
    val chore: DocumentReference,
    val doneBy: String,
    val doneWhen: Date
)