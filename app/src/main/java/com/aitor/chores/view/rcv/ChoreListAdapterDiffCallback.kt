package com.aitor.chores.view.rcv

import androidx.recyclerview.widget.DiffUtil
import com.aitor.chores.view.rcv.ChoreListAdapter.ChoreListItem

class ChoreListAdapterDiffCallback : DiffUtil.ItemCallback<ChoreListItem>() {
    override fun areItemsTheSame(oldChoreListItem: ChoreListItem, newChoreListItem: ChoreListItem): Boolean {
        return oldChoreListItem == newChoreListItem
    }

    override fun areContentsTheSame(oldChoreListItem: ChoreListItem, newChoreListItem: ChoreListItem): Boolean {
        return oldChoreListItem == newChoreListItem
    }
}