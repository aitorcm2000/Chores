package com.aitor.chores.view.rcv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.databinding.ChoreLinearLayoutBinding

class ChoresAdapter :
    ListAdapter<ChoresItem, ChoresAdapter.ViewHolder>(ChoreItemDiffCallback()) {

    class ViewHolder(private val binding : ChoreLinearLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: ChoresItem) {
            binding.titulo.text = item.completedChore.doneBy
            binding.fecha.text = item.completedChore.doneWhen.toDate().toString()
            // Puedes agregar un listener para manejar los clicks si es necesario
            binding.root.setOnClickListener {

            }
        }
    }

    //Por cada elemento nuevo deberia cambiar entre derecha e izquierda
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