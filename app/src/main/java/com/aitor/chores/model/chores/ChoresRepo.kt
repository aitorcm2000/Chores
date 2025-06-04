package com.aitor.chores.model.chores

import android.content.Context

object ChoresRepo {
    var myChore = mutableListOf<ChoreObject>()

    fun getById (id: String) : ChoreObject{
        for (chore in myChore){
            if (chore.id == id) return chore

        }
        return ChoreObject("ERROR","","","")
    }
}