package com.aitor.chores.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.View

import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.databinding.ActivityMainBinding
import com.aitor.chores.view.controllers.mainmenu.MainMenuController
import com.aitor.chores.view.controllers.mainmenu.MainMenuSetup
import com.aitor.chores.view.rcv.MenuItemAdapter_Main

class MainActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var setup: MainMenuSetup
    private lateinit var binding: ActivityMainBinding
    private lateinit var rcv: RecyclerView
    private lateinit var adapterMain: MenuItemAdapter_Main

    private val viewModel: MainMenuController by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapterMain = MenuItemAdapter_Main()

        rcv = findViewById(R.id.recycler)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapterMain


        viewModel.menuitems.observe(this) { datos ->
            adapterMain.submitList(datos)
        }

        db = FirestoreConnection().startConnection()
        setup = MainMenuSetup(this, this, binding, db)

//        rcv = findViewById(R.id.baseFrame)
//        rcv.addItemDecoration(RCV_Deco_List(20))
//        rcv.setLayoutManager(LinearLayoutManager(this))
//        val rcv1_adapter = MenuItemAdapter_Main(datos)
//        rcv.adapter = rcv1_adapter

        init_Toolbar()


//        setup.setMenuOnClick(rcv.findViewHolderForItemId(0).itemView)

//        var boton = findViewById<Button>(R.id.button1)
//
//        boton.setOnClickListener(){
//            val intent = Intent(this,TareasActivity::class.java)
//            startActivity(intent)
//        }

    }

    fun init_Toolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
//        db = FirestoreConnection().startConnection()
    }

    override fun onDestroy() {
        super.onDestroy()
//        db.terminate()
    }

    fun setMenuOnClick(view: View) {
        // Handle the click here
//        setup.setMenuOnClick(view)
    }
}