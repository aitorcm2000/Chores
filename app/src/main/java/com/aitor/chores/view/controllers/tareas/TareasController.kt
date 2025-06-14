package com.aitor.chores.view.controllers.tareas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.R
import com.aitor.chores.view.rcv.DatosMenuItem

class TareasController : ViewModel()  {

    private val _menuitems = MutableLiveData<List<DatosMenuItem>>()
    var menuitems: LiveData<List<DatosMenuItem>?> = _menuitems

    init {
        _menuitems.value = listOf(
            DatosMenuItem("Ropa de Cama",R.drawable.sabanas),
            DatosMenuItem("Toallas",R.drawable.towels),
            DatosMenuItem("Polvo",R.drawable.polvo),
            DatosMenuItem("Limpiar",R.drawable.cocina),
            DatosMenuItem("Aspirar",R.drawable.aspiradora),
            DatosMenuItem("Ventanas",R.drawable.ventanas),
        )
    }

    fun addItem(item: DatosMenuItem) {
        val currentList = _menuitems.value?.toMutableList() ?: mutableListOf()
        currentList.add(item)
        _menuitems.value = currentList
    }
}