package com.aitor.chores.model.users_groups

import com.aitor.chores.model.chores.ChoreObject
import com.google.firebase.firestore.DocumentReference
import java.util.Date

data class UserGroupChoresObjectResponse (
    val chore: DocumentReference,
    val doneBy: String,
    val doneWhen: Date
)