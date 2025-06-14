package com.aitor.chores.view.components.ui

import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

/**
 * Clase que implementa la interfaz ISnackBarFactory para crear snackbars personalizados
 */
class SnackBarFactory : ISnackBarFactory {

    override fun createSnackBar(view: View, message: String, duration: Int, gravity: Int): Snackbar {
        val snackbar = Snackbar.make(view, message, duration)
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
        params.gravity = gravity
        snackbarView.layoutParams = params
        return snackbar
    }
}