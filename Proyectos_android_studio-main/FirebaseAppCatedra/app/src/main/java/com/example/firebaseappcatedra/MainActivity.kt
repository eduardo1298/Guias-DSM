package com.example.firebaseappcatedra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.firebaseappcatedra.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.navBottomNavigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        AppBarConfiguration(navController.graph)
        navView.setupWithNavController(navController)



       // binding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        //binding.signOutImageView.setOnClickListener{
       //     signOut()
       // }



    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }

}