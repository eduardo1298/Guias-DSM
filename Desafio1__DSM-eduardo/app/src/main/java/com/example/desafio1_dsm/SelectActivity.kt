package com.example.desafio1_dsm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var btnEjercicio1 : Button
lateinit var btnEjercicio2 : Button
lateinit var btnRegresar : Button

class SelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        //Importamos los botones
        btnEjercicio1 = findViewById(R.id.btnEjercicio1)
        btnEjercicio2 = findViewById(R.id.btnEjercicio2)
        btnRegresar = findViewById(R.id.btnRegresar)

        //Abrimos la actividad correspondiente a cada boton
        //Ejercicio 1
        btnEjercicio1.setOnClickListener{
            val intentE1 = Intent(this,Ejercicio1Activity::class.java)
            startActivity(intentE1)
        }

        //Ejercicio 2
        btnEjercicio2.setOnClickListener{
            val intentE2 = Intent(this,Ejercicio2Activity::class.java)
            startActivity(intentE2)
        }

        //Cerrar Sesion
        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}