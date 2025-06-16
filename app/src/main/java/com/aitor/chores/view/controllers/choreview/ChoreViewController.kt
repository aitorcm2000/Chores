package com.aitor.chores.view.controllers.choreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.view.components.utils.Event
import com.aitor.chores.view.rcv.ChoresItem

class ChoreViewController : ViewModel() {

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