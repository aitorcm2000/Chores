package com.aitor.chores.view.activities

import android.os.Bundle
import android.view.Gravity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.databinding.ActivityChoresBinding
import com.aitor.chores.view.components.ui.SnackBarFactory
import com.aitor.chores.view.controllers.choreview.ChoreViewController
import com.aitor.chores.view.rcv.ChoresAdapter
import com.aitor.chores.view.rcv.RCV_Deco_List
import com.google.android.material.snackbar.Snackbar

class ChoresActivity : AppCompatActivity() {

    private lateinit var rcv: RecyclerView
    private lateinit var binding: ActivityChoresBinding
    private val viewModel: ChoreViewController by viewModels() // Ahora esto debería funcionar sin el factory
    private lateinit var adapter: ChoresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // *** IMPORTANTE: Inflar el binding y establecer el contentView UNA SOLA VEZ ***
        binding = ActivityChoresBinding.inflate(layoutInflater)
        setContentView(binding.root) // Usa la raíz del binding como el contenido

        // Ahora, ViewCompat.setOnApplyWindowInsetsListener usa la vista raíz CORRECTA
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("choreName")

        if (name!= null) {
            viewModel.setChoreName(name)
        }

        adapter = ChoresAdapter()

        // Accede al RecyclerView a través del binding, es más seguro y limpio
        rcv =
            binding.recycler // Suponiendo que el ID de tu RecyclerView es 'recycler' en activity_chores.xml
        rcv.addItemDecoration(RCV_Deco_List(20))
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter




        // Observar LiveData del ViewModel
        viewModel.menuitems.observe(this) { datos ->
            adapter.submitList(datos)
        }

        viewModel.setChore()

        viewModel.loadList()
        // Configura el listener para los eventos del ViewModel
        viewModelEventsListener()

        binding.button.setOnClickListener {
            viewModel.addItem() // Llama a la función del ViewModel que emite el mensaje
        }


    }

    private fun viewModelEventsListener() {
        viewModel.mensaje.observe(this) { event ->
            // Usa el wrapper Event para asegurar que el mensaje solo se consuma una vez
            event.getContentIfNotHandled()?.let { message ->
                SnackBarFactory()
                    .createSnackBar(binding.root, message, Snackbar.LENGTH_LONG, Gravity.CENTER)
                    .show()
            }
        }
    }
}