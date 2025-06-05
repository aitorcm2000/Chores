package com.aitor.chores.model.users

import com.aitor.chores.model.chores.ChoreInputObject
import java.util.Date

data class UserChoresObject (
    val chore : ChoreInputObject,
    val doneWhen : Date,
    val groupID : String
)
