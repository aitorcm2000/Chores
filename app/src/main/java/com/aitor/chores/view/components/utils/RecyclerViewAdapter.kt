package com.aitor.chores.view.components.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Cambiamos la interfaz a una CLASE ABSTRACTA que extiende de RecyclerView.Adapter
abstract class RecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(
    protected val items: MutableList<T>
) : RecyclerView.Adapter<VH>() { // <--- ¡Ahora extiende de RecyclerView.Adapter!

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract override fun onBindViewHolder(holder: VH, position: Int)

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): T {
        return items[position]
    }

    open fun removeItem(position: Int) {
        if (position >= 0 && position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun restoreItem(item: T, position: Int) {
        if (position >= 0 && position <= items.size) {
            items.add(position, item)
            notifyItemInserted(position)
        }
    }
}