package com.aitor.chores.model.users

import com.aitor.chores.model.chores.ChoreObject
import java.util.Date

data class UserChoresObject (
    val chore : ChoreObject,
    val doneWhen : Date,
    val groupID : String
)
