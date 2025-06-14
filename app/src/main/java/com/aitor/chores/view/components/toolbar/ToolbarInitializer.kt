package com.aitor.chores.view.components.toolbar

import android.view.Menu

interface ToolbarInitializer{
    fun setupToolbar(itemId: Int, menu: Int, title: String?)
    fun configureToolbarMenu(toolbar: Int, menu: Menu?)
}
