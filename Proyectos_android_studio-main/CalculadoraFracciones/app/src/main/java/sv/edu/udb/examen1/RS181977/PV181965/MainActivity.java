package sv.edu.udb.examen1.RS181977.PV181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaramos variables
    TextView Fraccion1, Fraccion2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Enlazando componentes del la pantalla principal
        Fraccion1 = (EditText) findViewById(R.id.Fraccion01);
        Fraccion2 = (EditText) findViewById(R.id.Fraccion02);
        final Button BotonCalcular = (Button) findViewById(R.id.BotonCalcular);

        BotonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //La propiedad error la actualizamos a un valor nulo
                Fraccion1.setError(null);
                Fraccion2.setError(null);
                //Expresión regular para verificar el formato de las entradas.
                final String regex = "[\\s-]*(\\d+)\\s*/\\s*(\\d+)";
                //En las siguientes condicionales se valida que los campos no se encuentren vacíos
                //y que tengan el formato de un número fraccionario
                if ("".equals(Fraccion1.getText().toString().trim())) {
                    Fraccion1.setError("Error.Ingrese la primera fracción"); //Muestra el mensaje de error
                    Fraccion1.requestFocus(); //Se enfoca el elemento
                    return;
                } else if ("".equals(Fraccion2.getText().toString().trim())) {
                    Fraccion2.setError("Error.Ingrese la segunda fracción"); //Muestra el mensaje de error
                    Fraccion2.requestFocus(); //Se enfoca el elemento
                } else if (Fraccion1.getText().toString().matches(regex) == false) {
                    Fraccion1.setError("Error.Fracción inválida. Formato correcto #/#"); //Muestra el mensaje de error
                    Fraccion1.requestFocus(); //Se enfoca el elemento
                } else if (Fraccion2.getText().toString().matches(regex) == false) {
                    Fraccion2.setError("Error.Fracción invá4lida. Formato correcto #/#"); //Muestra el mensaje de error
                    Fraccion2.requestFocus(); //Se enfoca el elemento
                } else {
                    //Dividimos la cadena ingresa en 2 partes, la primera es el númerador
                    // y la segunda el denominador
                    //si se ingresa 5/2 el numerador="5" y denominador="2"
                    String[] partes = Fraccion1.getText().toString().split("/");
                    String numerador1 = partes[0];
                    String denominador1 = partes[1];

                    String[] partes2 = Fraccion2.getText().toString().split("/");
                    String numerador2 = partes2[0];
                    String denominador2 = partes2[1];
                    if ("0".equals(denominador1)) {
                        Fraccion1.setError("Error.El denominador no puede ser 0"); //Muestra el mensaje de error
                        Fraccion1.requestFocus(); //Se enfoca el elemento
                    } else if ("0".equals(denominador2)) {
                        Fraccion2.setError("Error.El denominador no puede ser 0"); //Muestra el mensaje de error
                        Fraccion2.requestFocus(); //Se enfoca el elemento
                    } else {
                        //Hacemos uso de intent para mandar los datos de la pantalla
                        // principal a la pantalla donde se muestra el resultado y pasamos a la otra pantalla.
                        Intent intent = new Intent(MainActivity.this, activity_fracciones.class);
                        Bundle b = new Bundle();
                        b.putString("NUMERADOR1", numerador1);
                        b.putString("DENOMINADOR1", denominador1);
                        b.putString("NUMERADOR2", numerador2);
                        b.putString("DENOMINADOR2", denominador2);
                        intent.putExtras(b);
                        startActivity(intent);
                    }
            }
        };




    });

    }

}

