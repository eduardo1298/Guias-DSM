package sv.edu.udb.RS181977.PV181965;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Resultado_Actividad extends AppCompatActivity {

    //Declaramos los atributos para trabajar los textos que se mostrarán en pantalla.
    private TextView cuadroTextox1;
    private TextView cuadroTextox2;
    private TextView texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_actividad);

        //Asignamos a cada atributo correspondiente el elemento de texto.

        cuadroTextox1 = (TextView) findViewById(R.id.Resultadox1); //Texto que Muestra el valor de x1.
        cuadroTextox2 = (TextView) findViewById(R.id.Resultadox2); //Texto que Muestra el valor de x2.
        texto2 = (TextView) findViewById(R.id.texto2); //Texto que Muestra la ecuación completa.


        Bundle extractor = getIntent().getExtras();//Declaramos objeto "Bundle" para extraer los parámetros que pasamos en la actividad principal.
        String x1 = extractor.getString("x1"); //Extraemos el valor de x1.
        String x2 = extractor.getString("x2"); //Extraemos el valor de x2.
        String coeficienteA = extractor.getString("A"); //Extraemos el valor del coeficiente A.
        String coeficienteB = extractor.getString("B"); //Extraemos el valor del coeficiente B.
        String coeficienteC = extractor.getString("C"); //Extraemos el valor del coeficiente C.


        String signoCoeficienteB; //Variable que mostrará signo "+" cuando el coeficiente B sea positivo.
        String signoCoeficienteC; //Variable que mostrará signo "+" cuando el coeficiente C sea positivo.


        //Condicional para formatear el signo que mostrará para el coeficienteB.
        if (Double.parseDouble(coeficienteB) >= 0) { //Si Coeficiente B es positivo, se muestra "+".
            signoCoeficienteB = "+";
        } else { //Si Coeficiente B es negativo, no se muestra nada porque el valor del coeficiente ya lo tiene.
            signoCoeficienteB = " ";
        }

        //Condicional para formatear el signo que mostrará para el coeficienteC.
        if (Double.parseDouble(coeficienteC) >= 0) {//Si Coeficiente C es positivo, se muestra "+".
            signoCoeficienteC = "+";
        } else {//Si Coeficiente C es negativo, no se muestra nada porque el valor del coeficiente ya lo tiene.
            signoCoeficienteC = " ";
        }

//Mostramos resultados en pantalla.
        texto2.setText("Ecuación: " + coeficienteA + "x2 " + signoCoeficienteB + coeficienteB + "x " + signoCoeficienteC + coeficienteC + " = 0"); //Mostramos la ecuación completa que ingresó el usuario.
        cuadroTextox1.setText(x1); //Mostramos el valor de x1 en pantalla.
        cuadroTextox2.setText(x2); //Mostramos el valor de x2 en pantalla.


    }


    public void Regresar(View vista) {
        finish();
    }


}