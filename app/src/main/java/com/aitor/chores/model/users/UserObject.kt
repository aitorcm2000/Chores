package com.aitor.chores.model.users

data class UserObject (
    val id : String,
    val username :String,
    val password :String,
    val email :String,
    val groups :List<String>,
    val choresCompleted :List<HashMap<String, Any>>)