package com.aitor.chores.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aitor.chores.R
import com.aitor.chores.communication.FirestoreConnection
import com.aitor.chores.communication.InnerJoins
import com.aitor.chores.communication.TableReferenceNames
import com.aitor.chores.communication.chores.ChoresGroupsQueries
import com.aitor.chores.communication.users.UsersQueries
import com.aitor.chores.communication.users_groups.UsersGroupQueries
import com.aitor.chores.model.chores.ChoreGroupObject
import com.aitor.chores.model.users.UserObject
import com.aitor.chores.view.rcv.DatosMenuItem
import com.aitor.chores.view.rcv.MenuItemAdapter_Main
import com.aitor.chores.view.rcv.RCV_Deco_List
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db : FirebaseFirestore

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


        db = FirestoreConnection().startConnection()

        var user : UserObject
        CoroutineScope(Dispatchers.IO).launch {
//            UsersGroupQueries(db.collection(TableReferenceNames.USER_GROUP))
//                .getGroupById("WR0KjtzfdutW12re8xBV")

            user = UsersQueries(db.collection(TableReferenceNames.USERS))
                .getUserById("eh8gTYna9rAnW64sUaR9") as UserObject

            InnerJoins(db).getAllChoresForUser(user)

//            val lista = ChoresGroupsQueries(db.collection(TableReferenceNames.CHORES_GROUP))
//                .getAllChoreGroups()
//            for (group in lista as List<ChoreGroupObject>){
//                println(group.chores)
//            }

//
//            for (ref in user.groups){
//                println(ref.toString().trim())
//                UsersGroupQueries(db.collection(TableReferenceNames.USER_GROUP))
//                    .getGroupById(ref.toString())
//            }
        }



//        db.collection("users").get().addOnSuccessListener { result ->
//            for (document in result) {
//                println("${document.id} => ${document.data}")
//                val user = UserObject(
//                    document.data.get("username").toString(),
//                    document.data.get("password").toString(),
//                    document.data.get("mail").toString(),
//                    document.data.get("groups") as List<DocumentReference>,
//                    document.data.get("chores_completed") as List<CompletedChoreObject>)
//
//                println(user.groups.get(0)as DocumentReference)
//                val ref = user.groups.get(0)as DocumentReference
//                ref.get().addOnSuccessListener { document ->
//                    println(document.id)
//                    db.collection("user_group").document(document.id.trim()).get().addOnSuccessListener { document ->
//                        println(document.data)
//                    }
//                }
//            }
//        }
//            .addOnFailureListener { exception ->
//                Log.e("Firestore", "Error al obtener documentos: ", exception)
//            }

        rcv = findViewById(R.id.rcv_1)
        rcv.addItemDecoration(RCV_Deco_List(20))
        rcv.setLayoutManager(LinearLayoutManager(this))
        val rcv1_adapter = MenuItemAdapter_Main(datos)
        rcv.adapter = rcv1_adapter

        init_Toolbar()

//        var boton = findViewById<Button>(R.id.button1)
//
//        boton.setOnClickListener(){
//            val intent = Intent(this,TareasActivity::class.java)
//            startActivity(intent)
//        }

    }

    fun init_Toolbar(){
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
        return true
    }

    fun setMenuOnClick(view: View) {
        // Handle the click here
        when (view.id) {
            R.id.MenuItem -> {
                // Do something when MenuItem is clicked
                val intent = Intent(this, TareasActivity::class.java)
                startActivity(intent)
            }
            // Add more cases for other views if needed
        }
    }


}