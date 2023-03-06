package com.example.firebasekotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.firebasekotlincrud.MainActivity
import com.example.firebasekotlincrud.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private val database = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.signUpButton.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()
            val mRepeatPassword = binding.repeatPasswordEditText.text.toString()
            val mName = binding.nameEditText.text.toString();

            binding.emailEditText.setError(null)
            binding.passwordEditText.setError(null)
            binding.repeatPasswordEditText.setError(null)
            binding.nameEditText.setError(null)

            if (mName.trim().isEmpty()) {

                binding.nameEditText.setError("Ingrese su nombre completo")
                binding.nameEditText.requestFocus()
            } else if (mEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {

                binding.emailEditText.setError("Ingrese un email valido")
                binding.emailEditText.requestFocus()
            } else if (mPassword.isEmpty() || mPassword.length<6) {
                binding.passwordEditText.setError("La contraseña debe tener mínimo 6 caracteres")
                binding.passwordEditText.requestFocus()
            } else if (mPassword != mRepeatPassword) {

                binding.repeatPasswordEditText.setError("Las contraseñas no son iguales")
                binding.repeatPasswordEditText.requestFocus()
            } else {
                createAccount(mEmail, mPassword,mName)
            }

        }

        binding.backImageView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                reload()
            } else {
                val intent = Intent(this, CheckEmailActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun createAccount(email: String, password: String,name:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Usuario Creado Correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = Firebase.auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }
                    user!!.updateProfile(profileUpdates)
                    val uid=user.uid
                    val myRef = database.getReference("users")

                    val email = email
                    val role = "turista"

                    val users = Users(uid,name,email,role)
                    myRef.child(uid.toString()).setValue(users)
                    finish()
                    val intent = Intent(this, CheckEmailActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "El correo ya ha sido registrado.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

    private fun reload() {
        val intent = Intent(this, Home::class.java)
        this.startActivity(intent)
    }
}