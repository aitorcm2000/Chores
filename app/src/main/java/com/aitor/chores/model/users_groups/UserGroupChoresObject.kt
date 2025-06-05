package com.aitor.chores.model.users_groups

import com.aitor.chores.model.chores.ChoreInputObject
import java.util.Date

data class UserGroupChoresObject (
    val chore: ChoreInputObject,
    val doneBy: String,
    val doneWhen: Date
)