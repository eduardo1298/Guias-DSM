package com.example.myapplicationcheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox CheckBoxLectura;
    private CheckBox CheckBoxPasear;
    private CheckBox CheckBoxSeriesTv;
    private CheckBox CheckBoxCompras;

    private Button BotonEnviar;
    private TextView TextViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBoxCompras=(CheckBox) findViewById(R.id.idCompras);
        CheckBoxPasear=(CheckBox) findViewById(R.id.idpasear);
        CheckBoxSeriesTv=(CheckBox) findViewById(R.id.idSeriesTv);
        CheckBoxLectura=(CheckBox) findViewById(R.id.idleer);
        TextViewResultado=(TextView) findViewById(R.id.textViewResultado);
        BotonEnviar=(Button) findViewById(R.id.buttonEnviar);

        BotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MensajeLectura="";
                String MensajeCompras="";
                String MensajeSeries="";
                String MensajePasear="";
                StringBuilder stringBuilder01=new StringBuilder();
                if(CheckBoxLectura.isChecked()==false){
                    MensajeLectura="No seleccionado";
                }else{
                    MensajeLectura="Seleccionado";
                }

                if(CheckBoxSeriesTv.isChecked()==false){
                    MensajeSeries="No seleccionado";
                }else{
                    MensajeSeries="Seleccionado";
                }

                if(CheckBoxCompras.isChecked()==false){
                    MensajeCompras="No seleccionado";
                }else{
                    MensajeCompras="Seleccionado";
                }
//ckbxPasear.isChecked()?"Seleccionado":"NO seleccionado"
                if(CheckBoxPasear.isChecked()==false){
                    MensajePasear="No seleccionado";
                }else{
                    MensajePasear="Seleccionado";
                }
                stringBuilder01.append(CheckBoxLectura.getText().toString() + ": " + CheckBoxLectura.isChecked()+"\n");
                stringBuilder01.append(CheckBoxCompras.getText().toString() + ": " + CheckBoxCompras.isChecked()+"\n");
                stringBuilder01.append(CheckBoxSeriesTv.getText().toString() + ": " + CheckBoxSeriesTv.isChecked()+"\n");
                stringBuilder01.append(CheckBoxPasear.getText().toString() + ": " + CheckBoxPasear.isChecked()+"\n");
                TextViewResultado.setText(stringBuilder01);
            }
        });


    }
}