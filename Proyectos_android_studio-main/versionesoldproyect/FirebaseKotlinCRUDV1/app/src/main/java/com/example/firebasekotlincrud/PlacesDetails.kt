package com.example.firebasekotlincrud

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.firebasekotlincrud.databinding.ActivityPlacesDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class PlacesDetails : AppCompatActivity() {
    private lateinit var binding: ActivityPlacesDetailsBinding;
    private lateinit var auth: FirebaseAuth;///
    private var isFavorito = false // Esto nos indica si el lugar está  agregado a favorito o no.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacesDetailsBinding.inflate(layoutInflater)

        auth = Firebase.auth///
        val currentUser = auth.currentUser///

        setContentView(binding.root)
        val key = intent.getStringExtra("key")

        val database = Firebase.database

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val myRef = database.getReference("places").child(key)

        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        /* binding.FavoriteActionButton.setOnClickListener {

             val favorite = true
             myRef.child("favorite").setValue(favorite)
             Toast.makeText(applicationContext, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
             finish()

         }*/
        /* binding.FavoriteActionButton.setOnLongClickListener {

             val favorite = false
             myRef.child("favorite").setValue(favorite)
             Toast.makeText(applicationContext, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
             finish()
             true
         }*/

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

        //Parte que se ejecuta al inicio de la activity y ejecuta el proceso para ver si está en favorito o no.

        if (currentUser != null) {
            checkfavorite(key, currentUser.uid.toString())
        } else {


        }

        //Botón para el proceso de agregar / quitar lugares de favoritos.


        binding.btnPlaceFavorite.setOnClickListener() {

            //Primero se verifica si el usuario ha iniciado sesión.
            if (currentUser == null) {

            } else {
                //  Toast.makeText(baseContext, "Usuario iniciado",Toast.LENGTH_SHORT).show()

                // Toast.makeText(baseContext, key,Toast.LENGTH_SHORT).show()
                // Toast.makeText(baseContext, currentUser.uid.toString(),Toast.LENGTH_SHORT).show()
                if (isFavorito) {
                    deletefavorite(key, currentUser.uid.toString())
                } else {
                    addFavorite(key, currentUser.uid.toString())
                }

            }

        }


    }


    private fun images(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(binding.posterImgeView)


    }

//Funciones para el proceso de agregar / quitar lugares de favoritos.

    private fun addFavorite(placesID: String, userID: String) {

        val hashMap = HashMap<String, Any>()
        val timestamp = System.currentTimeMillis();
        hashMap["placesID"] = placesID
        // hashMap["timestamp"] = timestamp

        //Guardamos a la base de datos.
        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.child(userID).child("Favorites").child(placesID).setValue(hashMap)
            .addOnSuccessListener {
                //Se agrega el lugar a favoritos.
                Toast.makeText(baseContext, "Se ha agregado a favoritos", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                //Falló el proceso para agregar a favoritos.
                Toast.makeText(baseContext, "Fallo:  ${e.message}", Toast.LENGTH_SHORT).show()

            }


    }


    private fun deletefavorite(placeID: String, userID: String) {
        //Referencia a la base de datos.
        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.child(userID).child("Favorites").child(placeID).removeValue()
            .addOnSuccessListener {

                Toast.makeText(baseContext, "Se ha eliminado de favoritos", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(baseContext, "Fallo:  ${e.message}", Toast.LENGTH_SHORT).show()
            }


    }

    private fun checkfavorite(placesID: String, userID: String) {

        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.child(userID).child("Favorites").child(placesID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    isFavorito = snapshot.exists()
                    if (isFavorito) {
                        Log.d("My Activity", "OnDataChange: available in fav")

                        //binding.btnLugarFavorito.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_star_filled))
                        binding.btnPlaceFavorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                baseContext,
                                R.drawable.ic_favorite_star_filled
                            )
                        )

                    } else {
                        Log.d("My Activity", "OnDataChange: not available in fav")

                        // binding.btnLugarFavorito.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_star_border))
                        binding.btnPlaceFavorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                baseContext,
                                R.drawable.ic_favorite_star_border
                            )
                        )
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //
                }
            })

    }


}









