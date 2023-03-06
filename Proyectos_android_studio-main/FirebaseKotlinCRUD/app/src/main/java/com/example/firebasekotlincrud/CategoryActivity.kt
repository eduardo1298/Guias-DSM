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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebasekotlincrud.databinding.ActivityCategoryBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_notes.view.*
import java.io.File


class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding
    private val database = Firebase.database
    private lateinit var messagesListener: ValueEventListener
    private val listPlaces:MutableList<PlacesTuristic> = ArrayList()

    var query = ""
    var myRef = database.getReference("places").orderByChild("type").equalTo(query)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.setOnClickListener(){
            val intent = Intent(this,Home::class.java)
            this.startActivity(intent)
        }

        binding.backImageView.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            startActivity(intent)
        }

        val valor = intent.getIntExtra("data", 1)
        if (valor == null){
            Snackbar.make(binding.root, "" + valor, Snackbar.LENGTH_SHORT).show()
        }else{
            when(valor) {
                1 -> {
                    query = "Playas"
                    binding.textoIn.setText(query)
                    myRef = database.getReference("places").orderByChild("type").equalTo(query)
                }
                2 -> {
                    query = "Centros Comerciales"
                    binding.textoIn.setText(query)
                    myRef = database.getReference("places").orderByChild("type").equalTo(query)
                }
                3 -> {
                    query = "Centros Turísticos"
                    binding.textoIn.setText(query)
                    myRef = database.getReference("places").orderByChild("type").equalTo(query)
                }
                4 -> {
                    query = "Lagos"
                    binding.textoIn.setText(query)
                    myRef = database.getReference("places").orderByChild("type").equalTo(query)
                }
                else -> {
                    error("No hay datos")
                }
            }

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
                    Log.w("TAGAAAAAAAAAAAAAAAAAAAA",places.key.toString())

                }
                it.context.startActivity(intent)
            }

            holder.itemView.setOnLongClickListener{
                val intent = Intent(it.context, EditActivity::class.java).apply {
                    putExtra("key", places.key)
                }
                it.context.startActivity(intent)
                true
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
