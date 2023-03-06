package com.example.firebasekotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasekotlincrud.R
import com.example.firebasekotlincrud.databinding.ActivityHomeBinding
import com.example.firebasekotlincrud.databinding.ActivitySignInBinding
import com.example.firebasekotlincrud.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityHomeBinding

    private val database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.buttonplaces.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonsignout.setOnClickListener() {
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(this, SignInActivity::class.java)
            this.startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        if (currentUser != null) {

            binding.tvHome.setText(currentUser.displayName + ", esta es tu pantalla de inicio")


        }
    }


}