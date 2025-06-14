package com.aitor.chores.view.controllers.mainmenu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityMainBinding
import com.aitor.chores.view.rcv.DatosMenuItem
import com.google.firebase.firestore.FirebaseFirestore

class MainMenuController(
//    private val activity : AppCompatActivity,
//    private val context : Context,
//    private val binding: ActivityMainBinding,
//    private val db : FirebaseFirestore
) : ViewModel(){

    private val _menuitems = MutableLiveData<List<DatosMenuItem>>()
    var menuitems: LiveData<List<DatosMenuItem>?> = _menuitems

    init {
        _menuitems.value = listOf(
            DatosMenuItem("Menu1", R.drawable.home),
            DatosMenuItem("Menu2", R.drawable.compra)
        )
    }

    fun addItem(item: DatosMenuItem) {
        val currentList = _menuitems.value?.toMutableList() ?: mutableListOf()
        currentList.add(item)
        _menuitems.value = currentList
    }
}