package com.aitor.chores.view.controllers.chorelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.communication.chores.ChoresPUD
import com.aitor.chores.model.chores.ChoreOutputObject
import com.aitor.chores.view.controllers.CommonData
import com.aitor.chores.view.rcv.ChoreListAdapter
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

    }

    fun loadChoreList() {
        val list = CommonData.completedChoresForUser(choreId.value)
        list.forEach {
            val item = ChoreListAdapter.ChoreListItem(
                it.chore.name,
                it.doneWhen
            )
            _choreList.value = _choreList.value?.plus(item)
        }
    }

    fun setChoreId(id: String) {
        _choreId.value = id
        loadChoreList()
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