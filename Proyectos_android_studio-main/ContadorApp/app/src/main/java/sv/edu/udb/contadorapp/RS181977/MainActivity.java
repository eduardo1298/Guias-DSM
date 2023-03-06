package sv.edu.udb.contadorapp.RS181977;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvcontador;
    //    Integer contador = 0;
    MainViewModel mainViewModel;
    Button btnsumar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcontador = findViewById(R.id.tvContador);
        btnsumar = findViewById(R.id.btnSumar);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.ObtenerContador().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvcontador.setText(String.valueOf(integer));
            }
        });

        btnsumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarcontador();
            }
        });
    }

    public void sumarcontador() {
        mainViewModel.AgregarContador();
    }

}