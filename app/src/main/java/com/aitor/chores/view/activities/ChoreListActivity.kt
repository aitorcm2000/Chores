package com.aitor.chores.view.activities

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.databinding.ActivityChoreListBinding
import com.aitor.chores.model.chores.ChoreOutputObject
import com.aitor.chores.view.controllers.CommonData
import com.aitor.chores.view.controllers.chorelist.ChoreListController
import com.aitor.chores.view.rcv.ChoreListAdapter

class ChoreListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChoreListBinding
    private val viewModel : ChoreListController by viewModels()
    private lateinit var rcv : RecyclerView
    private lateinit var adapter : ChoreListAdapter

    private lateinit var choreId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityChoreListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("choreGroup")

        if (id!= null) {
            choreId = id
            viewModel.setChoreGroupId(choreId)
        }

        adapter = ChoreListAdapter()
        rcv = binding.recycler
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter

        viewModel.choreList.observe(this) { datos ->
            adapter.submitList(datos)
        }

        binding.buttonAddChore.setOnClickListener {
            viewModel.setText(binding.editTextChoreList.toString())
            showConfirmationDialog()
        }

    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Confirmar Acción")

        builder.setMessage("¿Estás seguro de que quieres realizar esta acción?")


        builder.setPositiveButton("Sí") { dialog: DialogInterface, which: Int ->

            Toast.makeText(this, "Acción confirmada y realizada.", Toast.LENGTH_SHORT).show()

            val chore = ChoreOutputObject(
                CommonData.user.id,
                choreId,
                binding.editTextChoreList.text.toString()
            )

            viewModel.buttonClicked(chore)
            viewModel.loadChoreList()
        }

        builder.setNegativeButton("No") { dialog: DialogInterface, which: Int ->

            Toast.makeText(this, "Acción cancelada.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setCancelable(false)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}