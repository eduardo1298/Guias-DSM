package com.example.dms_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCreacteOptionMenu(menu: Menu?):Boolean{
        val  inflater= menuInflater
        inflater.inflate(R.menu.menu_muestra,menu)
        return true
    }
}