package com.aitor.chores.model.chores

import java.util.Date

data class CompletedChoreObject (
    val chore : ChoreInputObject,
    val doneWhen: Date,
    val doneBy: String)