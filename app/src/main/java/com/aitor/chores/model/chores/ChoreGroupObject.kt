package com.aitor.chores.model.chores

data class ChoreGroupObject (
    val id : String,
    val name : String,
    val chores : List<String>
    )