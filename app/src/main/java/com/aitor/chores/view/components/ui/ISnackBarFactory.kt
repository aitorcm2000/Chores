package com.aitor.chores.view.components.ui

import android.view.View
import com.google.android.material.snackbar.Snackbar

interface ISnackBarFactory {
    fun createSnackBar(view: View, message: String, duration: Int, gravity: Int): Snackbar
}