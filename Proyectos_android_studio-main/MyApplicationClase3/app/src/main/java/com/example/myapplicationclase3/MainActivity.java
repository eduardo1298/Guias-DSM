package com.example.myapplicationclase3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtNombre=(EditText) findViewById(R.id.TxtName);
        final EditText txtCarnet=(EditText) findViewById(R.id.TxtCarnet);
        final EditText txtCum=(EditText) findViewById(R.id.TxtCum);
        final Button btnCum=(Button) findViewById(R.id.BtnCum);

        btnCum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent= new Intent(MainActivity.this,FrmCum.class);
                Bundle b = new Bundle();
                b.putString("NOMBRE",txtNombre.getText().toString());
                b.putString("CARNET",txtCarnet.getText().toString());
                b.putString("CUM",txtCum.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}