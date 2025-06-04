package com.aitor.chores.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.view.rcv.DatosMenuItem
import com.aitor.chores.view.rcv.MenuItemAdapter_Mini
import com.aitor.chores.view.rcv.RCV_Deco_Colum

class TareasActivity : AppCompatActivity() {
    private lateinit var rcv: RecyclerView
    private val datos : ArrayList<DatosMenuItem> = arrayListOf(
        DatosMenuItem("Menu1"),
        DatosMenuItem("Menu2"),
        DatosMenuItem("Menu3"),
        DatosMenuItem("Menu4"),
        DatosMenuItem("Menu5"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tareas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rcv = findViewById(R.id.rcv_2)
        rcv.addItemDecoration(RCV_Deco_Colum(10, 2))
        rcv.setLayoutManager(GridLayoutManager(this , 2))
        val rcv1_adapter = MenuItemAdapter_Mini(datos)
        rcv.adapter = rcv1_adapter
    }
}