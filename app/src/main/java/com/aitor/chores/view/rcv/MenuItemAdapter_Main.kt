package com.aitor.chores.view.rcv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.databinding.MainMenuItemBinding
import com.aitor.chores.view.activities.RegisterActivity
import com.aitor.chores.view.activities.TareasActivity

class MenuItemAdapter_Main :
    ListAdapter<DatosMenuItem, MenuItemAdapter_Main.ViewHolder>(MenuItemDiffCallback()) {

    class ViewHolder(private val binding : MainMenuItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: DatosMenuItem) {
            binding.tituloMainMenu.text = item.title
            binding.imgMenu.setImageResource(item.img)
            // Puedes agregar un listener para manejar los clicks si es necesario
            binding.root.setOnClickListener {
                if (item.title == "Menu1") {
                    // Aquí puedes manejar el click en el primer elemento
                    // Por ejemplo, iniciar una nueva actividad o mostrar un mensaje
                    binding.root.context.startActivity(
                        Intent(binding.root.context, TareasActivity::class.java)
                    )
                } else if (item.title == "Menu2") {

                    binding.root.context.startActivity(
                        Intent(binding.root.context, RegisterActivity::class.java)
                    )
                    // Manejar el click en el segundo elemento
                    // Por ejemplo, iniciar otra actividad o mostrar un mensaje diferente
                }
            }
        }
    }

    //Por cada elemento nuevo deberia cambiar entre derecha e izquierda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainMenuItemBinding.inflate(
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