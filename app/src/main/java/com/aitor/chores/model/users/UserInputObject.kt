package com.aitor.chores.model.users

class UserInputObject{

    val id : String
    val username :String
    val password :String
    val email :String
    val groups :List<String>
    val choresCompleted :List<HashMap<String, Any>>

    constructor() {
        this.id = ""
        this.username = ""
        this.password = ""
        this.email = ""
        this.groups = emptyList()
        this.choresCompleted = emptyList()
    }

    constructor(
        id: String,
        username: String,
        password: String,
        email: String,
        groups: List<String>,
        choresCompleted: List<HashMap<String, Any>>
    ) {
        this.id = id
        this.username = username
        this.password = password
        this.email = email
        this.groups = groups
        this.choresCompleted = choresCompleted
    }
}

