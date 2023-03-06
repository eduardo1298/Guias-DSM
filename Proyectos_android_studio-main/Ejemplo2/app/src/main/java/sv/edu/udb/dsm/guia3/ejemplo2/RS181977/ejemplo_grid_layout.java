package sv.edu.udb.dsm.guia3.ejemplo2.RS181977;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import sv.edu.udb.dsm.guia3.ejemplo2.R;

public class ejemplo_grid_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_grid_layout);
    }
    public void finalizarActividad(View v){
        finish();
    }
}