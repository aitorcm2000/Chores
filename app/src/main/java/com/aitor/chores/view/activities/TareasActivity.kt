package com.aitor.chores.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityTypeBinding
import com.aitor.chores.view.controllers.tareas.TareasController
import com.aitor.chores.view.rcv.MenuItemAdapter_Mini
import com.aitor.chores.view.rcv.RCV_Deco_Colum

class TareasActivity : AppCompatActivity() {
    private lateinit var rcv: RecyclerView
    private lateinit var binding : ActivityTypeBinding
    private val viewModel : TareasController by viewModels()
    private lateinit var adapterMini: MenuItemAdapter_Mini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapterMini = MenuItemAdapter_Mini()

        rcv = findViewById(R.id.recycler)
        rcv.addItemDecoration(RCV_Deco_Colum(100, 20, 2, GridLayoutManager.VERTICAL))
        rcv.setLayoutManager(GridLayoutManager(this , 2))
        rcv.adapter = adapterMini


        viewModel.menuitems.observe(this) { datos ->
            adapterMini.submitList(datos)
        }
    }
}