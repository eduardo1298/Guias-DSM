package com.example.firebasekotlincrud

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlincrud.databinding.ActivityAdminhomeBinding
import com.example.firebasekotlincrud.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class HomeAdmin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAdminhomeBinding
    private val database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminhomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonplaces.setOnClickListener(){
            val intent = Intent(this,MainActivityAdmin::class.java)
            startActivity(intent)
        }

        binding.buttonsignout.setOnClickListener(){
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(this,SignInActivity::class.java)
            this.startActivity(intent)
        }

    }
}