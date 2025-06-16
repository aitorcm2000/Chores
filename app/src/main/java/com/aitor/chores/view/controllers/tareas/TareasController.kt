package com.aitor.chores.view.controllers.tareas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.R
import com.aitor.chores.communication.chores.ChoresGroupsQueries
import com.aitor.chores.view.controllers.CommonData
import com.aitor.chores.view.rcv.DatosMenuItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TareasController : ViewModel() {

    private val _menuitems = MutableLiveData<List<DatosMenuItem>>()
    var menuitems: LiveData<List<DatosMenuItem>?> = _menuitems

    init {
        _menuitems.value = listOf()
        loadMenuItems()
    }

    fun loadMenuItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val groups = ChoresGroupsQueries().getAllChoreGroups()

            var item = DatosMenuItem("", R.drawable.ic_launcher_foreground)
            for (group in groups) {
                when (group.name) {
                    "sheets" -> {
                        item = DatosMenuItem(group.id, R.drawable.sabanas)
                    }

                    "towels" -> {
                        item = DatosMenuItem(group.id, R.drawable.towels)
                    }

                    "dusting" -> {
                        item = DatosMenuItem(group.id, R.drawable.polvo)
                    }

                    "cleaning" -> {
                        item = DatosMenuItem(group.id, R.drawable.cocina)
                    }

                    "vacuum" -> {
                        item = DatosMenuItem(group.id, R.drawable.aspiradora)
                    }

                    "windows" -> {
                        item = DatosMenuItem(group.id, R.drawable.ventanas)
                    }
                }

                withContext(Dispatchers.Main) {
                    if (item.title != "") {
                        addItem(item)
                    }
                }


            }
        }


    }

    fun addItem(item: DatosMenuItem) {
        val currentList = _menuitems.value?.toMutableList() ?: mutableListOf()
        currentList.add(item)
        _menuitems.value = currentList
    }
}