package sv.edu.udb.myapplicationspinnercombobox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Spinner spinner01;
    TextView txtView01;
    TextView txtView02;

    Button btnboton01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner01=(Spinner)findViewById(R.id.spinner01);
        btnboton01=(Button)findViewById(R.id.btnBoton01);
        txtView01=(TextView)findViewById(R.id.textView01);
        txtView02=(TextView)findViewById(R.id.textView02);
        btnboton01.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           txtView02.setText(" Opción Seleccionada : "+" "+spinner01.getSelectedItem().toString());
       }
   });
    }
}