package com.aitor.chores.view.rcv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.databinding.ChoreLinearLayoutBinding
import com.google.firebase.Timestamp

class ChoreListAdapter
    : ListAdapter<ChoreListAdapter.ChoreListItem, ChoreListAdapter.ViewHolder>(ChoreListAdapterDiffCallback()) {

    data class ChoreListItem(
        val titulo: String,
        val fecha: Timestamp
        )

    class ViewHolder(private val binding: ChoreLinearLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(choreListItem: ChoreListItem) {
            binding.titulo.text = choreListItem.titulo
            binding.fecha.text = choreListItem.fecha.toDate().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ChoreLinearLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}