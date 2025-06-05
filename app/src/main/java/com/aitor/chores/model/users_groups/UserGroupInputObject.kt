package com.aitor.chores.model.users_groups

data class UserGroupInputObject (
    val id : String,
    val groupName : String,
    val members : List<HashMap<String, String>>,
    val choresCompleted : List<HashMap<String, Any>>
)