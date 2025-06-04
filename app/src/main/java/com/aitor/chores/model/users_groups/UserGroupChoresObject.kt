package com.aitor.chores.model.users_groups

import com.aitor.chores.model.chores.ChoreObject
import java.util.Date

data class UserGroupChoresObject (
    val chore: ChoreObject,
    val doneBy: String,
    val doneWhen: Date
)