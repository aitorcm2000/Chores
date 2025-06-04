package com.aitor.chores.model.users_groups

import com.aitor.chores.model.chores.CompletedChoreObject

data class UserGroupObject (
    val id : String,
    val groupName : String,
    val members : List<UserGroupMemberObject>,
    val choresCompleted : List<UserGroupChoresObject>
//    val members : List<Any>,//UserGroupMemberObject
//    val choresCompleted : List<Any>//CompletedChoreObject
)