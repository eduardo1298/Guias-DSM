package com.example.firebasekotlincrud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlincrud.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    private lateinit var database: DatabaseReference
    private lateinit var user: Users
    var role: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inicializar firebase auth
        auth = Firebase.auth
        //instanciamos el componente de inicio de sesion , al hacer click en ese componente ejecute la funcion
        binding.signInAppCompatButton.setOnClickListener {
            val mEmail = binding.emailEditText.text.toString()
            val mPassword = binding.passwordEditText.text.toString()

            when {
                mEmail.isEmpty() || mPassword.isEmpty() -> {
                    Toast.makeText(
                        baseContext, "Correo o contraseña vacío.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    SignIn(mEmail, mPassword)
                }
            }

        }




        binding.signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.recoveryAccountTextView.setOnClickListener {
            val intent = Intent(this, AccountRecoveryActivity::class.java)
            startActivity(intent)
        }


    }


    public override fun onStart() {
        super.onStart()
        // Verifica si el usuario está logueado
        val currentUser = auth.currentUser
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {

                val currentUser = auth.currentUser
                val iduser = currentUser?.uid
                val databaseRef = FirebaseDatabase.getInstance().getReference("users/")
                var userRef = databaseRef.child(currentUser!!.uid)

                userRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        role = dataSnapshot.child("role").value.toString()
                        Log.w("TAG", "$role")
                        loadhome(role.toString())
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message

                        Log.w("TAG", "Email o contraseña incorrecta", databaseError.toException())
                    //    Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                })
            } else {
                val intent = Intent(this, CheckEmailActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun SignIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val iduser = currentUser?.uid
                    val databaseRef = FirebaseDatabase.getInstance().getReference("users/")
                    var userRef = databaseRef.child(currentUser!!.uid)

                    userRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            role = dataSnapshot.child("role").value.toString()
                            Log.w("TAG", "$role")
                            loadhome(role.toString())
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Getting Post failed, log a message
                            Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                            // ...
                        }
                    })
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)


                    Toast.makeText(
                        baseContext, "Email o contraseña incorrecto",
                        Toast.LENGTH_SHORT
                    ).show()

                   /* Toast.makeText(
                        baseContext, task.exception.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                    */

                }
            }
    }

    private fun loadhome(role: String) {

        if (role.equals("turista")) {
            val intent = Intent(this, Home::class.java)
            this.startActivity(intent)
        } else {
            val intent = Intent(this, HomeAdmin::class.java)
            this.startActivity(intent)
        }

        Log.d("TAG", "signInWithEmail:success")
    }

    private fun reload() {
        val intent = Intent(this, Home::class.java)
        this.startActivity(intent)
    }
}