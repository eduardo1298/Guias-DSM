package com.example.myapplicationradio01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup01;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup01 = (RadioGroup) findViewById(R.id.radioGroupInformatica);
        radioGroup01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup Group, int idSeleccionado) {
                radioButton=(RadioButton)findViewById(idSeleccionado);
                switch (radioButton.getId()){
                    case R.id.radioButtonRedes:{
                        if(radioButton.isChecked())
                            Log.d("Redes","Seleccionado");
                    }
                    break;
                    case R.id.radioButtonDesarrollo:{
                        if(radioButton.isChecked())
                            Log.d("Desarrollo","Seleccionado");
                    }
                    break;
                    case R.id.radioButtonAuditoria:{
                        if(radioButton.isChecked())
                            Log.d("Auditoria","Seleccionado");
                    }
                    break;

                }
            }
        });

    };



    }
