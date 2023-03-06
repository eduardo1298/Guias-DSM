package com.example.myapplicationrecycleprueba01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.MiAdapter;
import Model.ListaItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView01;
    private RecyclerView.Adapter adapter01;
    private List<ListaItem> listaItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView01 = (RecyclerView) findViewById(R.id.recycleViewID);
        recyclerView01.setHasFixedSize(true);
        recyclerView01.setLayoutManager(new LinearLayoutManager(this));

        listaItems = new ArrayList<>();

        // Postariormente las siguientes lineas seran sustituidas con codigo donde
        // la informacion sera obtenida de tablas de una Base de Datos
        ListaItem item01 = new ListaItem("Desarrollo de Software para Moviles", "DSM104",4,"Multiplataforma");
        ListaItem item02 = new ListaItem("Java Avanzado", "JAA104",4,"Java Básico");
        ListaItem item03 = new ListaItem("Sistemas Operativos", "SIO104",4,"Arquitectura de pc");
        ListaItem item04 = new ListaItem("Programacion Estructurada", "PRE104",4,"Bachillerato");
        ListaItem item05 = new ListaItem("Programacion Orientada a Objetos", "POO104",4,"Programacion Estructurada");


        listaItems.add(item01);
        listaItems.add(item02);
        listaItems.add(item03);
        listaItems.add(item04);
        listaItems.add(item05);


        adapter01 = new MiAdapter(this, listaItems);
        recyclerView01.setAdapter(adapter01);
    }
}