package com.example.firebaseappcatedra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseappcatedra.databinding.ActivityCheckEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class CheckEmailActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityCheckEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        val user = auth.currentUser

        binding.veficateEmailAppCompatButton.setOnClickListener{
            val profileUpdate = userProfileChangeRequest {  }

            user!!.updateProfile(profileUpdate).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    if(user.isEmailVerified){
                        reload()
                    }else{
                        Toast.makeText(baseContext, "Por favor verifica tu correo electronico",
                            Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        binding.signOutImageView.setOnClickListener{
            signOut()
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            if(currentUser.isEmailVerified){
                reload()
            }else{
                sendEmailVerification()
            }

        }
    }

    private fun reload(){
        val intent = Intent(this,MainActivity::class.java)
        this.startActivity(intent)
    }

    private fun sendEmailVerification(){
        val user = auth.currentUser
        user!!.sendEmailVerification().addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "Se envio un correo de verificación",
                    Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
    }
}