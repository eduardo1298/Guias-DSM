package com.example.desafio1_dsm

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText

lateinit var txtUser : EditText
lateinit var txtPass : EditText
lateinit var btnAcceder : Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Importando componentes de la Actividad
        txtUser = findViewById(R.id.txtUser)
        txtPass = findViewById(R.id.txtPass)
        btnAcceder = findViewById(R.id.btnAcceder)

        //Creando mensaje de error
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Error en el ingreso a la aplicacion")
        builder.setMessage("El nombre del usuario y/o contra estan erroneos")
        builder.setIcon(R.drawable.ic_launcher_background)
        builder.setPositiveButton("OK",DialogInterface.OnClickListener { dialog, i -> dialog.dismiss() })

        val alertDialog:AlertDialog = builder.create()

        //Boton acceder a la aplicacion
        btnAcceder.setOnClickListener{
            var user : String = txtUser.text.toString()
            var pass : String = txtPass.text.toString()
            if (user == "admin" && pass == "123456"){
                val intent = Intent(this, SelectActivity::class.java)
                startActivity(intent)
            } else {
                alertDialog.show()
            }
        }
    }
}