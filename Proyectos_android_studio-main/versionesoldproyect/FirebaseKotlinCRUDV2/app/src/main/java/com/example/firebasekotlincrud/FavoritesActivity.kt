package com.example.firebasekotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasekotlincrud.databinding.ActivityFavoritesBinding
import com.example.firebasekotlincrud.repository.AdapterFavorites
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList


class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding;
    private val database = Firebase.database
    private lateinit var messagesListener: ValueEventListener


    //Autenticación de Firebase.
    private lateinit var firebaseAuth: FirebaseAuth

    //ArrayList para contener lugares.
    private lateinit var placesArrayList: ArrayList<Places>
    private lateinit var adapterFavorites: AdapterFavorites


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        Loadfavoriteplaces()
        //listPlaces.clear()
        //  setupRecyclerView(binding.placesRecyclerView)

        binding.logo.setOnClickListener() {
            val intent = Intent(this, Home::class.java)
            this.startActivity(intent)
        }

        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun Loadfavoriteplaces() {
        //Iniciamos arraylist.
        placesArrayList = ArrayList();
        val ref = FirebaseDatabase.getInstance().getReference("users")
        ref.child(firebaseAuth.uid!!).child("Favorites")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//Limpiamos arraylist, antes de comenzar a agregar datos.
                    placesArrayList.clear();

                    for (ds in snapshot.children) {
                        //obtener solo el id de los lugares, el resto de la información la tenemos cargada en la clase de adaptador (AdaptadorFavoritos)
                        val Id = "${ds.child("ID").value}"

                        //Asignamos a model (clase Places).
                        val modeloLugar = Places()
                        modeloLugar.id = Id
                        //   Toast.makeText(baseContext, "id del lugar: " + lugarId, Toast.LENGTH_SHORT).show()
                        //Agregamos el modelo a la lista.
                        placesArrayList.add(modeloLugar)


                    }

                    if (placesArrayList.size == 0) {
                        binding.message.text = "Aún no has agregado lugares favoritos."
                    } else {
                        binding.message.text = ""
                    }

                    //
                    //Configurar adaptador.
                    adapterFavorites = AdapterFavorites(this@FavoritesActivity, placesArrayList)
                    //Asignamos adaptador al recyclerview.
                    binding.placesRecyclerView.adapter = adapterFavorites
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }


/* ANTIGUO:
    private lateinit var binding : ActivityFavoritesBinding;
    private val database = Firebase.database
    private lateinit var messagesListener: ValueEventListener
    private val listPlaces:MutableList<PlacesTuristic> = ArrayList()
    val myRef = database.getReference("places").orderByChild("favorite").equalTo(true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        listPlaces.clear()
        setupRecyclerView(binding.placesRecyclerView)

        binding.logo.setOnClickListener(){
            val intent = Intent(this,Home::class.java)
            this.startActivity(intent)
        }

        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        messagesListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listPlaces.clear()

                dataSnapshot.children.forEach { child ->
                    val places: PlacesTuristic? =
                        PlacesTuristic(child.child("name").getValue<String>(),
                            child.child("location").getValue<String>(),
                            child.child("description").getValue<String>(),
                            child.child("favorite").getValue<Boolean>(),
                            child.child("url").getValue<String>(),
                            child.child("type").getValue<String>(),
                            child.key)
                    places?.let { listPlaces.add(it) }
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
                    putExtra("favorite", places.favorite)
                }
                it.context.startActivity(intent)
            }

         /*   holder.itemView.setOnLongClickListener{
                val intent = Intent(it.context, EditActivity::class.java).apply {
                    putExtra("key", places.key)
                }
                it.context.startActivity(intent)
                true
            }*/

        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val mNameTextView: TextView = view.findViewById(R.id.nameTextView)
            val mDateTextView: TextView = view.findViewById(R.id.dateTextView)
            val mPosterImgeView: ImageView? = view.findViewById(R.id.posterImgeView)

        }
    }


*/


}