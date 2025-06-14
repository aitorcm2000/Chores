package com.aitor.chores.view.rcv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityMainBinding
import com.aitor.chores.databinding.MenuItemDerBinding
import com.aitor.chores.view.activities.RegisterActivity
import com.aitor.chores.view.activities.TareasActivity

class MenuItemAdapter_Main :
    ListAdapter<DatosMenuItem, MenuItemAdapter_Main.ViewHolder>(MenuItemDiffCallback()) {

    class ViewHolder(private val binding : MenuItemDerBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: DatosMenuItem) {
            binding.tvTituloMenu.text = item.title
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
        val binding = MenuItemDerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
//        val titulo = datos[position].title
//        val imagen = datos[position].img
//        holder.titulo.text = titulo
//        holder.img.setImageResource(imagen)
    }
}