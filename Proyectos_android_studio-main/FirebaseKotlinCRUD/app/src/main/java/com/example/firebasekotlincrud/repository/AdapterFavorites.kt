package com.example.firebasekotlincrud.repository

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasekotlincrud.Places
import com.example.firebasekotlincrud.PlacesDetails
import kotlin.collections.ArrayList
import com.example.firebasekotlincrud.databinding.ListPlacesFavoritesBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdapterFavorites: RecyclerView.Adapter <AdapterFavorites.Holderplacefavorite>{

    private val context: Context

    private var placesArrayList: ArrayList<Places>

    private lateinit var binding: ListPlacesFavoritesBinding

    //Constructor.
    constructor(context: Context, placesArrayList: ArrayList<Places>) {
        this.context = context
        this.placesArrayList = placesArrayList
    }


    //Clase ViewHolder para administrar las vistas UI para list_places_favorites.xml
    inner class Holderplacefavorite(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Iniciamos Vistas UI.
        var titleplace = binding.titleplaces
        var locationplace = binding.locationplace
        var imgplace = binding.imgPlace


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holderplacefavorite {
       binding = ListPlacesFavoritesBinding.inflate(LayoutInflater.from(context),parent,false)

        return Holderplacefavorite(binding.root)
    }

    override fun onBindViewHolder(holder: Holderplacefavorite, position: Int) {
        // Obtener datos, asignar datos, encangarse de los "clicks", etc.

        //Obtener datos: de [users>uid>Favoritos] solo tenemos los ids de los lugares favoritos así que tenemos que cargar los detalles desde el nodo [places] de nuestra BD de firebase.
            val model = placesArrayList[position]


        LoadDetailsPlaces(model,holder)

        //controlar los "clicks", pasar id de los lugares para cargar la información.
        holder.itemView.setOnClickListener{
            val intent = Intent(context, PlacesDetails::class.java)
            intent.putExtra("key", model.id) //Pasamos el id del lugar.
            context.startActivity(intent)
        }
    }

    private fun LoadDetailsPlaces(model: Places, holder: AdapterFavorites.Holderplacefavorite) {
       val lugarId = model.id
        //Toast.makeText(context, "id del lugar: " + lugarId, Toast.LENGTH_SHORT).show()
        val ref = FirebaseDatabase.getInstance().getReference("places")
        ref.child(lugarId).addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               //Obtenemos la información del lugar.
                    val Id = "${snapshot.child("id").value}"
                    val name = "${snapshot.child("name").value}"
                    val location ="${snapshot.child("location").value}"
                    val Url = "${snapshot.child("url").value}"

                //Asignar datos a model.
                model.name = name
                model.location = location
                model.url = Url
                ///
                ///
                holder.titleplace.text = name
                holder.locationplace.text = location
                Glide
                    .with(context)
                    .load(Url) // the uri you got from Firebase

                    .into(holder.imgplace); //Your imageView variable
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    override fun getItemCount(): Int {
        return placesArrayList.size //Devuelve el tamaño de la lista | Número de items en lista.
    }


}