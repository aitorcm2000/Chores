package com.aitor.chores.view.rcv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.databinding.MenuItemDerBinding
import com.aitor.chores.databinding.MiniMenuBinding
import com.aitor.chores.view.activities.RegisterActivity
import com.aitor.chores.view.activities.TareasActivity

class MenuItemAdapter_Mini :
    ListAdapter<DatosMenuItem, MenuItemAdapter_Mini.ViewHolder>(MenuItemDiffCallback()) {

    class ViewHolder(private val binding: MiniMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DatosMenuItem) {

            binding.miniMenuImg.setImageResource(item.img)

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, TareasActivity::class.java)
                intent.putExtra("choreGroup", item.title)
                binding.root.context.startActivity(intent)
            }
        }
    }

    //Por cada elemento nuevo deberia cambiar entre derecha e izquierda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MiniMenuBinding.inflate(
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