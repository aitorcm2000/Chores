package com.aitor.chores.view.components.ui

import android.widget.Button

class ButtonControl {
    /**
     * Activa el botón de validación
     */
    fun activarBoton(button : Button) {
        button.isEnabled = true
        button.alpha = 1f
    }

    /**
     * Desactiva el botón de validación
     */
    fun desactivarBoton(button : Button) {
        button.isEnabled = false
        button.alpha = 0.5f
    }
}