package com.aitor.chores.rcv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R

class MenuItemAdapter_Main  (private val datos: List<DatosMenuItem>):
    RecyclerView.Adapter<MenuItemAdapter_Main.ViewHolder?>() {

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val titulo : TextView = v.findViewById(R.id.tv_titulo_menu)
        val img : ImageView = v.findViewById(R.id.img_menu)
    }

    //Por cada elemento nuevo deberia cambiar entre derecha e izquierda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item_der,parent,false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val titulo = datos[position].Titulo
        holder.titulo.text = titulo
        holder.img.setImageResource(R.drawable.yayah)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

}