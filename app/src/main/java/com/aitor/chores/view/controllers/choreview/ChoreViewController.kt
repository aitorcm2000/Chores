package com.aitor.chores.view.controllers.choreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.communication.chores.ChoresPUD
import com.aitor.chores.communication.chores.ChoresReferenceNames
import com.aitor.chores.communication.users.UsersPUD
import com.aitor.chores.model.chores.ChoreInputObject
import com.aitor.chores.model.chores.CompletedChoreObject
import com.aitor.chores.model.users.UserInputObject
import com.aitor.chores.view.components.utils.Event
import com.aitor.chores.view.controllers.CommonData
import com.aitor.chores.view.rcv.ChoreListAdapter
import com.aitor.chores.view.rcv.ChoresItem
import com.google.firebase.Timestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChoreViewController : ViewModel() {

    private val _menuitems = MutableLiveData<List<ChoresItem>>()
    var menuitems: LiveData<List<ChoresItem>?> = _menuitems

    private val _mensaje = MutableLiveData<Event<String>>()
    val mensaje: LiveData<Event<String>> = _mensaje

    private val _choreName = MutableLiveData<String>()
    val choreName: LiveData<String> = _choreName

    private val _chore = MutableLiveData<ChoreInputObject>()
    val chore: LiveData<ChoreInputObject> = _chore

    init {
        _menuitems.value = listOf()
    }

    fun setChore(){
        val chore = CommonData.userChores.find {

            it.name == choreName.value && it.group == CommonData.lastGroupId
        }

        if (chore != null) {
            _chore.value = chore!!
            _choreName.value = chore.name
        }
    }

    fun loadList() {
        val unfcompletedList = CommonData.completedChoresForUserByType(CommonData.lastGroupId)

        if (unfcompletedList.isEmpty()) {
            _menuitems.value = listOf()
        } else {
            unfcompletedList.forEach { listItem ->
                val item = ChoresItem(listItem)
                if (listItem.chore.name == _choreName.value) {
                    _menuitems.value = _menuitems.value?.plus(item)
                }
            }
        }
    }


    fun setChoreName(name: String) {
        _choreName.value = name
    }

    fun addItem() {
        val updatedUser = CommonData.user
        val completedChore = HashMap<String, Any>()
        completedChore.put(ChoresReferenceNames.CHORES, _chore.value.id)
        completedChore.put(ChoresReferenceNames.DONEBY, updatedUser.id)
        completedChore.put(ChoresReferenceNames.DONEWHEN, Timestamp.now())

        updatedUser.choresCompleted.add(completedChore)

        CoroutineScope(Dispatchers.IO).launch{
            UsersPUD().updateChore(updatedUser)
        }

        loadList()
        _mensaje.value = Event("Añadido")
        println("ChoresController: addItem called")
    }
}