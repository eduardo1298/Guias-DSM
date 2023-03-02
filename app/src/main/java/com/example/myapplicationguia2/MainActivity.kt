package com.example.myapplicationguia2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var nombre: EditText
lateinit var apellido: EditText
lateinit var enviar: Button
lateinit var  resultado: TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById(R.id.TextNombre)
        apellido= findViewById(R.id.TextApellido)

        resultado = findViewById(R.id.LlbResultado)
        enviar= findViewById(R.id.BtnEnviar)



        enviar.setOnClickListener{
            var nombreUsuario:String=nombre.text.toString()
            var apellidoUsuario:String= apellido.text.toString()
            resultado.setText(nombreUsuario + apellidoUsuario)
        }
    }
}