package udb.edu.sv.appsalary.RS181977;

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

         EditText EtNombre=(EditText) findViewById(R.id.Et_Nombre);
         EditText EtHoras=(EditText) findViewById(R.id.Et_Horas);
         Button BtnSalario=(Button) findViewById(R.id.Btn_Salario);

        BtnSalario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EtNombre.setError(null);
                    EtHoras.setError(null);

                Intent intent= new Intent(MainActivity.this, FrmSalary.class);
                Bundle b = new Bundle();
                b.putString("Nombre",EtNombre.getText().toString());
                b.putString("Horas",EtHoras.getText().toString());
                final String regex = "^[0-9]\\d*(.\\d+)?$";


                if ("".equals(EtNombre.getText().toString())) {
                    EtNombre.setError("Error.Ingresa tu nombre");
                    EtNombre.requestFocus();
                    return;
                }else if("".equals(EtHoras.getText().toString())){

                    EtHoras.setError("Error.Ingresa el número de horas trabajadas");
                    EtHoras.requestFocus();
                    return;
                }else if(EtHoras.getText().toString().matches(regex)==false){
                    EtHoras.setError("Error.Solo se aceptan números positivos");
                    EtHoras.requestFocus();
                    return;
                }
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}