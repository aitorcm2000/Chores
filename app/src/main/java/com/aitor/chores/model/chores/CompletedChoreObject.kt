package com.aitor.chores.model.chores

import com.google.firebase.Timestamp
import java.util.Date

data class CompletedChoreObject (
    val chore : ChoreInputObject,
    val doneWhen: Timestamp,
    val doneBy: String)