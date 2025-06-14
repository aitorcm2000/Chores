package com.aitor.chores.view.components.toolbar


import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Clase que implementa la interfaz ToolbarInitializer
 */
class ToolbarManager(private val activity: AppCompatActivity) : ToolbarInitializer {
    /**
     * Configura la Toolbar con el layout personalizado
     */
    override fun setupToolbar(itemId: Int, menu: Int, title: String?) {
        val toolbar: Toolbar = activity.findViewById(itemId)
        activity.setSupportActionBar(toolbar)
        toolbar.inflateMenu(menu)
        if (title != null) activity.supportActionBar?.title = title
    }

    /**
     * Configura el menú de la Toolbar
     */
    override fun configureToolbarMenu(toolbar: Int, menu: Menu?) {
        activity.menuInflater.inflate(toolbar, menu)
    }
}