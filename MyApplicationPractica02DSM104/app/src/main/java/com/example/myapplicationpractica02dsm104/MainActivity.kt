package com.example.myapplicationpractica02dsm104

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var nombre : EditText
lateinit var enviar : Button
lateinit var resultado : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById(R.id.TxtNombre)
        enviar = findViewById(R.id.BtnEnviar)
        resultado = findViewById(R.id.LibResultado)

        enviar.setOnClickListener{
            var nombreUsuario:String = nombre.text.toString()
            resultado.setText(nombreUsuario)
        }
    }
}