package com.aitor.chores.model.chores

object ChoresRepo {
    var myChore = mutableListOf<ChoreInputObject>()

    fun getById (id: String) : ChoreInputObject{
        for (chore in myChore){
            if (chore.id == id) return chore

        }
        return ChoreInputObject("ERROR", "", "", "")
    }
}