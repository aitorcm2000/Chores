package com.aitor.chores.view.rcv

import androidx.recyclerview.widget.DiffUtil

class MenuItemDiffCallback : DiffUtil.ItemCallback<DatosMenuItem>()  {
    override fun areItemsTheSame(oldItem: DatosMenuItem, newItem: DatosMenuItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DatosMenuItem, newItem: DatosMenuItem): Boolean {
        return oldItem == newItem
    }
}