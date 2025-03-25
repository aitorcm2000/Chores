package com.aitor.chores

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.rcv.DatosMenuItem
import com.aitor.chores.rcv.MenuItemAdapter_Main
import com.aitor.chores.rcv.RCV_Deco_List

class MainActivity : AppCompatActivity() {
    private lateinit var rcv: RecyclerView
    private val datos : ArrayList<DatosMenuItem> = arrayListOf(
        DatosMenuItem("Menu1"),
        DatosMenuItem("Menu2")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rcv = findViewById(R.id.rcv_1)
        rcv.addItemDecoration(RCV_Deco_List(20))
        rcv.setLayoutManager(LinearLayoutManager(this))
        val rcv1_adapter = MenuItemAdapter_Main(datos)
        rcv.adapter = rcv1_adapter

        var boton = findViewById<Button>(R.id.button1)

        boton.setOnClickListener(){
            val intent = Intent(this,TareasActivity::class.java)
            startActivity(intent)
        }

    }

    fun init_Toolbar(){
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //toolbar.inflateMenu(R.menu)
    }


}