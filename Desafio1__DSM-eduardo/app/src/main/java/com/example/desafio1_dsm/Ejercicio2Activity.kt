package com.example.desafio1_dsm

import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow
import kotlin.math.sqrt

lateinit var txtNum1 : EditText
lateinit var txtNum2 : EditText
lateinit var txtNum3 : EditText
lateinit var lbResultado1 : TextView
lateinit var lbResultado2 : TextView
lateinit var btnCalcular : Button
lateinit var btnRegresarE2 : Button

class Ejercicio2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        //Importando los elementos de la actividad
        txtNum1 = findViewById(R.id.txtNum1)
        txtNum2 = findViewById(R.id.txtNum2)
        txtNum3 = findViewById(R.id.txtNum3)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnRegresarE2 = findViewById(R.id.btnRegresarE2)
        lbResultado1 = findViewById(R.id.lbResultado1)
        lbResultado2 = findViewById(R.id.lbResultado2)

        //Creando mensaje de error
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Hay campos vacios")
        builder.setMessage("Uno de los numeros no ha sido digitado todavia")
        builder.setIcon(R.drawable.ic_launcher_background)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, i -> dialog.dismiss() })

        val alertDialog: AlertDialog = builder.create()

        //Calcular la Ec. Cuadratica
        btnCalcular.setOnClickListener{
            //Guardamos los numeros en variables
            var a : Double = txtNum1.text.toString().toDouble()
            var b : Double = txtNum2.text.toString().toDouble()
            var c : Double = txtNum3.text.toString().toDouble()

            Log.d("NUMEROS",a.toString())
            Log.d("NUMEROS",b.toString())
            Log.d("NUMEROS",c.toString())

            if (a == null)  {
                a = 0.0
             } else if (b == null)  {
                b = 0.0
            } else if (c == null)  {
                c = 0.0
            }

            // Validamos que todos los valores estan digitados
            if (a != 0.0 || b != 0.0 || c != 0.0 ) {

                Log.d("NUMEROS",a.toString())
                Log.d("NUMEROS",b.toString())
                Log.d("NUMEROS",c.toString())

                //Calculamos la ecuacion cuadratica
                var x1 : Double = (-b+sqrt(b.pow(2) -4*a*c))/(2*a)
                val x2 : Double = (-b-sqrt(b.pow(2) -4*a*c))/(2*a)

                //Imprimiendo los resultados en pantalla
                //Evaluamos si tiene solucion
                if (x1.isNaN() || x2.isNaN()) {
                    lbResultado1.text = "LA ECUACION NO TIENE SOLUCION"
                    lbResultado2.text = ""
                } else{
                    lbResultado1.text = "X1: $x1"
                    lbResultado2.text = "X2: $x2"
                }



            } else {
                alertDialog.show()
            }
        }

        //Regresar
        btnRegresarE2.setOnClickListener{
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)
        }



    }
}