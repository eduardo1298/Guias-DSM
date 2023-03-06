package com.example.firebasekotlincrud


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlincrud.databinding.ActivityAddBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*


class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var url:String?=null
    private val database = Firebase.database
    private val options =
        arrayListOf("Playas", "Lagos", "Centros Comerciales", "Centros Turísticos")

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        val myRef = database.getReference("places")
        val name = binding.nameEditText.text
        val location = binding.dateEditText.text
        val description = binding.descriptionEditText.text

     //   val favorite = false


        val spinner = findViewById<Spinner>(R.id.spinnerkotlin)


        var type = ""


        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {


                    val id = spinner.selectedItemId.toInt()
                    when (id) {
                        0 -> type = "Playas"

                        1 -> type = "Lagos"

                        2 -> type = "Centros Comerciales"

                        3 -> type = "Centros Turísticos"

                        else -> error("selecicona")

                    }

                }


                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        binding.Buttonimg.setOnClickListener() {
            selectImage();

        }
        binding.saveButton.setOnClickListener {
            with(binding) {
                if (name.isNullOrBlank() || location.isNullOrBlank() || description.isNullOrBlank() || filePath === null) {
                    Snackbar.make(this.root, "Algunos campos estan vacios", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    uploadImage(type)
                }
            }
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Seleccione una foto"),
            PICK_IMAGE_REQUEST
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun addplace(type:String,path:String) {
        val myRef = database.getReference("places")
        val name = binding.nameEditText.text
        val location = binding.dateEditText.text
        val description = binding.descriptionEditText.text
       // val favorite = false
        val ID = myRef.push().key.toString()
        val places = PlacesTuristic(
            ID,
            name.toString().lowercase(),
            location.toString(),
            description.toString(),
            //favorite,
            filePath.toString(),
            type,
            "pendiente"
        )
        myRef.child(myRef.push().key.toString()).setValue(places)

        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            filePath = data.data
            binding.Imageplace.setImageURI(filePath)
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage(type: String) {
        if (filePath != null) {
            val ref = storageReference?.child("myImages/" + UUID.randomUUID().toString())
            ref?.putFile(filePath!!)?.addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    filePath=it;
                    addplace(type,it.toString())

                }
            }

        }
    }

}