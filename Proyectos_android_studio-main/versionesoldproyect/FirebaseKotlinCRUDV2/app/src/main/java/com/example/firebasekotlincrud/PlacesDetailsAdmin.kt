package com.example.firebasekotlincrud

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.firebasekotlincrud.databinding.ActivityPlacesDetailsAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PlacesDetailsAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityPlacesDetailsAdminBinding;
    private lateinit var auth: FirebaseAuth;///


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesDetailsAdminBinding.inflate(layoutInflater)

        auth = Firebase.auth///
        val currentUser = auth.currentUser///

        setContentView(binding.root)
        val key = intent.getStringExtra("key")

        val database = Firebase.database

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val myRef = database.getReference("places").child(key)



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val places: PlacesTuristic? = dataSnapshot.getValue(PlacesTuristic::class.java)
                if (places != null) {
                    binding.nameTextView.text = places.name.toString()
                    binding.dateTextView.text = places.location.toString()
                    binding.descriptionTextView.text = places.description.toString()
                    binding.typeTextView.text = "Categoría: " + places.type.toString()
                    images(places.url.toString())
                    Log.w("TAG", places.url.toString())

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })


        //Botón para el proceso de agregar / quitar lugares de favoritos.


        binding.btnAcceptPlace.setOnClickListener() {

            //Primero se verifica si el usuario ha iniciado sesión.
            if (currentUser != null) {

                val ad: android.app.AlertDialog.Builder =
                    android.app.AlertDialog.Builder(this@PlacesDetailsAdmin,R.style.MyDialogTheme)
                ad.setMessage("Está seguro de aceptar el siguiente registro?")
                    .setTitle("Confirmación")

                ad.setPositiveButton("Si",
                    DialogInterface.OnClickListener { dialog, id ->
                        var status="aprobado";

                        myRef.child("status").setValue(status)
                        Toast.makeText(
                            this@PlacesDetailsAdmin,
                            "Lugar aceptado", Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this,MainActivityAdmin::class.java)
                        this.startActivity(intent)
                    })
                ad.setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(
                            this@PlacesDetailsAdmin,
                            "Se ha cancelado la operación", Toast.LENGTH_SHORT
                        ).show()
                    })

                ad.show()
            }

        }

        binding.btnDeclinePlace.setOnClickListener() {

            //Primero se verifica si el usuario ha iniciado sesión.
            if (currentUser != null) {
                val ad: android.app.AlertDialog.Builder =
                    android.app.AlertDialog.Builder(this@PlacesDetailsAdmin,R.style.MyDialogTheme)
                ad.setMessage("Está seguro de rechazar el siguiente registro?")
                    .setTitle("Confirmación")
                ad.setPositiveButton("Si",
                    DialogInterface.OnClickListener { dialog, id ->
                        myRef.removeValue()
                        val intent = Intent(this,MainActivityAdmin::class.java)
                        this.startActivity(intent)
                        Toast.makeText(
                            this@PlacesDetailsAdmin,
                            "Lugar rechazado", Toast.LENGTH_SHORT
                        ).show()
                    })
                ad.setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(
                            this@PlacesDetailsAdmin,
                            "Se ha cancelado la operación", Toast.LENGTH_SHORT
                        ).show()
                    })

                ad.show()
            }

        }


    }


    private fun images(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(binding.posterImgeView)


    }







}









