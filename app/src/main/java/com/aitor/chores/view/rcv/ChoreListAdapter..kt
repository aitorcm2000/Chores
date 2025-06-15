package com.aitor.chores.view.rcv

import androidx.recyclerview.widget.ListAdapter

class ChoreListAdapter
    : ListAdapter<ChoreListAdapter.Item, ChoreListAdapter.ViewHolder>(ChoreListAdapterDifCallback()) {

    data class Item(val a: Any)

    class ChoreListAdapterDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val binding: Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = Binding.inflate(
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