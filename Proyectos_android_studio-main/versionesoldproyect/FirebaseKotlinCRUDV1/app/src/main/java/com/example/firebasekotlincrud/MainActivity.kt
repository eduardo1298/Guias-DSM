package com.example.firebasekotlincrud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasekotlincrud.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private val database = Firebase.database
    private lateinit var messagesListener: ValueEventListener
    private val listPlaces: MutableList<PlacesTuristic> = ArrayList()
    val myRef = database.getReference("places")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.setOnClickListener() {
            val intent = Intent(this, Home::class.java)
            this.startActivity(intent)
        }

        binding.newFloatingActionButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            it.context.startActivity(intent)
        }
        binding.favorite.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            it.context.startActivity(intent)
        }
        binding.category.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            it.context.startActivity(intent)
        }
        binding.search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            it.context.startActivity(intent)
        }
        binding.notes.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            it.context.startActivity(intent)
        }

        listPlaces.clear()
        setupRecyclerView(binding.placesRecyclerView)

    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        messagesListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listPlaces.clear()

                dataSnapshot.children.forEach { child ->
                    val places: PlacesTuristic? =
                        PlacesTuristic(
                            child.child("id").getValue<String>(),
                            child.child("name").getValue<String>(),
                            child.child("location").getValue<String>(),
                            child.child("description").getValue<String>(),
                            //child.child("favorite").getValue<Boolean>(),
                            child.child("url").getValue<String>(),
                            child.child("type").getValue<String>(),
                            child.child("status").getValue<String>(),
                            child.key

                        )


                    if("aprobado".equals((child.child("status").getValue()).toString())){
                        places?.let { listPlaces.add(it) }
                    }



                }
                recyclerView.adapter = PlacesViewAdapter(listPlaces)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "messages:onCancelled: ${error.message}")
            }
        }
        myRef.addValueEventListener(messagesListener)


    }

    class PlacesViewAdapter(private val values: List<PlacesTuristic>) :
        RecyclerView.Adapter<PlacesViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.places_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val places = values[position]

            holder.mNameTextView.text = places.name
            holder.mDateTextView.text = places.location
            holder.mPosterImgeView?.let {
                Glide.with(holder.itemView.context)
                    .load(places.url)
                    .into(it)
            }

            holder.itemView.setOnClickListener {
                val intent = Intent(it.context, PlacesDetails::class.java).apply {
                    putExtra("key", places.key)
                    // putExtra("favorite", places.favorite)
                }
                it.context.startActivity(intent)
            }


        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val mNameTextView: TextView = view.findViewById(R.id.nameTextView)
            val mDateTextView: TextView = view.findViewById(R.id.dateTextView)
            val mPosterImgeView: ImageView? = view.findViewById(R.id.posterImgeView)

        }
    }


}

