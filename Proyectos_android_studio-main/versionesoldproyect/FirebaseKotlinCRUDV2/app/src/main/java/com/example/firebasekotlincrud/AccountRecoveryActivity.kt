package com.example.firebasekotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasekotlincrud.databinding.ActivityAccountRecoveryBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountRecoveryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountRecoveryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityAccountRecoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backImageView.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            this.startActivity(intent)
        }

        binding.senEmailAppCompatButton.setOnClickListener{
            val emailAddress = binding.emailEditText.text.toString()
            Firebase.auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                val intent = Intent(this,SignInActivity::class.java)
                    this.startActivity(intent)
                }else{
                    Toast.makeText(baseContext, "Ingrese el email de una cuenta existente",
                        Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}