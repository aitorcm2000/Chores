package com.aitor.chores.view.controllers.chores

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.aitor.chores.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class ChoresController (
    private val activity: AppCompatActivity,
    private val context: Context,
    private val binding: ActivityLoginBinding,
    private val db: FirebaseFirestore
) {

}