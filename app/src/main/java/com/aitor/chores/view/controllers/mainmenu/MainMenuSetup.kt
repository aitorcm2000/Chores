package com.aitor.chores.view.controllers.mainmenu

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityMainBinding
import com.aitor.chores.view.activities.TareasActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainMenuSetup(
    private val activity : AppCompatActivity,
    private val context : Context,
    private val binding: ActivityMainBinding,
    private val db : FirebaseFirestore
){

//    fun setMenuOnClick(view: View) {
//        // Handle the click here
//        when (view.id) {
//            R.id.MenuItem -> {
//                // Do something when MenuItem is clicked
//                val intent =  Intent(activity, TareasActivity::class.java)
//                activity.startActivity(intent)
//            }
//
//
//        }
//    }
}