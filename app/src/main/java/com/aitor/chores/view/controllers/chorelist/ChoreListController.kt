package com.aitor.chores.view.controllers.chorelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.communication.chores.ChoresPUD
import com.aitor.chores.model.chores.ChoreOutputObject
import com.aitor.chores.view.controllers.CommonData
import com.aitor.chores.view.rcv.ChoreListAdapter
import com.google.firebase.Timestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChoreListController : ViewModel(){

    private val _choreList = MutableLiveData<List<ChoreListAdapter.ChoreListItem>>()
    val choreList: LiveData<List<ChoreListAdapter.ChoreListItem>> = _choreList

    private val _choreId = MutableLiveData<String>()
    val choreId: LiveData<String> = _choreId

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text


    init {
        _choreList.value = emptyList()

    }

    fun loadChoreList() {

        val list = CommonData.completedChoresForUser(_choreId.value)

        if (list.isEmpty()) {
            val chore_List = CommonData.userChores
            if (chore_List.isEmpty()) {
                _choreList.value = emptyList()
                return
            } else {
                chore_List.forEach { listItem ->
                    if (listItem.group == _choreId.value) {
                        val item = ChoreListAdapter.ChoreListItem(
                            listItem.name,
                            Timestamp.now()
                        )
                        _choreList.value = _choreList.value?.plus(item)
                    }
                }
            }
        }
        list.forEach { listItem ->
            val item = ChoreListAdapter.ChoreListItem(
                listItem.chore.name,
                listItem.doneWhen
            )

            val lista = _choreList.value.filter {
                listItem.chore.name == it.titulo
            }
            lista.forEach {
                if (it.titulo != listItem.chore.name) {
                    _choreList.value = listOf(item)
                }
            }

            _choreList.value = _choreList.value?.plus(item)
        }

    }

    fun setChoreGroupId(id: String) {
        _choreId.value = id
        CommonData.lastGroupId = _choreId.value.toString()
        loadChoreList()
    }

    fun setChoreId(id: String) {
        CommonData.lastChoreId = id
    }

    fun setText(text: String) {
        _text.value = text
    }

    fun buttonClicked(chore : ChoreOutputObject) {
        // Handle button click logic here
        CoroutineScope(Dispatchers.IO).launch {
            ChoresPUD().addChore(chore)
        }

    }
}