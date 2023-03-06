package com.example.desafio1_dsm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var btnRegresarE1 : Button


class Ejercicio1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        //Importando los botones del formulario
        btnRegresarE1 = findViewById(R.id.btnRegresarE1)


        //Acciones de los botones




        btnRegresarE1.setOnClickListener{
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)
        }


    }
}