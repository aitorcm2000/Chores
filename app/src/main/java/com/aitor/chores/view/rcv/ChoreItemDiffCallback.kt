package com.aitor.chores.view.rcv

import androidx.recyclerview.widget.DiffUtil

class ChoreItemDiffCallback : DiffUtil.ItemCallback<ChoresItem>()  {
    override fun areItemsTheSame(oldItem: ChoresItem, newItem: ChoresItem): Boolean {
        return oldItem.completedChore.doneBy == newItem.completedChore.doneBy
                && oldItem.completedChore.doneWhen == newItem.completedChore.doneWhen
    }

    override fun areContentsTheSame(oldItem: ChoresItem, newItem: ChoresItem): Boolean {
        return oldItem == newItem
    }
}