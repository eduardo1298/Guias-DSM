package com.example.myapplicationclase3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FrmCum extends AppCompatActivity {
    Float cum;
    Integer uv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cum);
        TextView txtsalidaNombre=(TextView) findViewById(R.id.TxtSalidaNombre);
        TextView txtsalidaCarnet=(TextView) findViewById(R.id.TxtSalidaCarnet);
        TextView txtsalidaCum=(TextView) findViewById(R.id.TxtSalidaCum);
        TextView txtsalidaUv=(TextView) findViewById(R.id.TxtSalidaUv);
        final Button btnCumSalida=(Button) findViewById(R.id.BtnSalirCum);

        Bundle bundle = this.getIntent().getExtras();

        txtsalidaNombre.setText("Nombre:"+bundle.getString("NOMBRE"));
        txtsalidaCarnet.setText("Carnet:"+bundle.getString("CARNET"));
        txtsalidaCum.setText("Cum:"+bundle.getString("CUM"));

        cum= Float.parseFloat(bundle.getString("CUM"));
        if(cum>=7.5){
            uv=32;
        }else if(cum>=7.0 && cum<7.5){
            uv=24;
        }else if(cum>=6.0 && cum<7.0){
            uv=20;
        }else{
            uv=16;
        }
        txtsalidaUv.setText("UV:"+uv.toString());

        btnCumSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(FrmCum.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
