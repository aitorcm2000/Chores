package com.aitor.chores.view.controllers.chores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.communication.chores.ChoresQueries
import com.aitor.chores.model.chores.ChoreInputObject
import com.aitor.chores.model.chores.CompletedChoreObject
import com.aitor.chores.view.components.ui.SnackBarFactory
import com.aitor.chores.view.components.utils.Event
import com.aitor.chores.view.rcv.ChoresItem
import com.google.firebase.firestore.FirebaseFirestore

class ChoresController : ViewModel() {

    // Accede a la instancia de Firestore a través del objeto Singleton
    private val tablas = FirestoreConnection.db?.collection(TableReferenceNames.CHORES)

    private val _menuitems = MutableLiveData<List<ChoresItem>>()
    var menuitems: LiveData<List<ChoresItem>?> = _menuitems

    private val _mensaje = MutableLiveData<Event<String>>()
    val mensaje: LiveData<Event<String>> = _mensaje

    init {
        _menuitems.value = listOf()
    }

    fun addItem() {
        _mensaje.value = Event("Añadido")
        println("ChoresController: addItem called")
    }
}