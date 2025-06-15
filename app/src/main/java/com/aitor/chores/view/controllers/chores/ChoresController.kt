package com.aitor.chores.view.controllers.chores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.communication.chores.ChoresQueries
import com.aitor.chores.model.chores.ChoreInputObject
import com.aitor.chores.model.chores.CompletedChoreObject
import com.aitor.chores.view.rcv.ChoresItem
import com.google.firebase.firestore.FirebaseFirestore

class ChoresController (
//    private val activity: AppCompatActivity,
//    private val context: Context,
//    private val binding: ActivityLoginBinding,
    private val db: FirebaseFirestore
) : ViewModel() {

    private val tablas = db.collection(TableReferenceNames.CHORES)

    private val _menuitems = MutableLiveData<List<ChoresItem>>()
    var menuitems: LiveData<List<ChoresItem>?> = _menuitems

    init {
        val choreList = ChoresQueries(db).getAllChoresForUser(pre)
        _menuitems.value = listOf(

        )
    }

    fun addItem() {
//        val currentList = _menuitems.value?.toMutableList() ?: mutableListOf()
//        currentList.add(item)
//        _menuitems.value = currentList
        
    }
}