package com.aitor.chores.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityChoresBinding
import com.aitor.chores.view.controllers.chores.ChoresController
import com.aitor.chores.view.rcv.ChoresAdapter
import com.aitor.chores.view.rcv.RCV_Deco_Colum
import com.aitor.chores.view.rcv.RCV_Deco_List

class ChoresActivity : AppCompatActivity() {

    private lateinit var rcv: RecyclerView
    private lateinit var binding : ActivityChoresBinding
    private val viewModel : ChoresController by viewModels()
    private lateinit var adapter: ChoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityChoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ChoresAdapter()

        rcv = findViewById(R.id.recycler)
        rcv.addItemDecoration(RCV_Deco_List(20))
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter


        viewModel.menuitems.observe(this) { datos ->
            adapter.submitList(datos)
        }
    }
}