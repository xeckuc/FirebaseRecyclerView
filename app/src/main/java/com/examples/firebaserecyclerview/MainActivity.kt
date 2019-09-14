package com.examples.firebaserecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val animals = arrayListOf<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimals.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        getData()
    }

    private fun getData() {

        val database = FirebaseFirestore.getInstance()
        val ref = database.collection("Animals")

        ref.get().addOnSuccessListener { result ->

            for ( document in result) {
                animals.add(
                    Animal(
                        document.data.getValue("name") as String,
                        document.data.getValue("imgUrl") as String
                    )
                )
                Log.d("Firebase", "${document.id} => ${document.data}")

                val adapter = AnimalRecyclerViewAdapter(this@MainActivity, animals)
                rvAnimals.adapter = adapter
            }
        }
            .addOnFailureListener { exception ->

                Log.w("Firebase", "Error getting documents", exception)
            }

    }
}
