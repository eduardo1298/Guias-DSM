package com.example.myapplication1pruebapractica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaramos tres atributos que corresponden a los tres coeficientes con los que
    //se trabaja en una ecuación cuadrática.
    private Double coeficienteA;
    private Double coeficienteB;
    private Double coeficienteC;

    //Se declara tres atributos de tipo "EditText" para posteriormente capturas los valores ingresados.
    private EditText cuadroTextoA;
    private EditText cuadroTextoB;
    private EditText cuadroTextoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignamos cada caja de texto a un atributo de la clase principal.
        cuadroTextoA = findViewById(R.id.datoA);
        cuadroTextoB = findViewById(R.id.datoB);
        cuadroTextoC = findViewById(R.id.datoC);


    }

    //Método que realiza el proceso para calcular la raíz cuadrática.
    public void resolverEcuacion(View vista) {

        Double discriminante, x1, x2; //Se declara el discriminante y los dos posibles valores de x.
        Double parteReal, parteImaginaria; //Se declara la parteReal y la parteImaginaria por si parte del resultado de la ecuación son números imaginarios.
        //Inicializamos las variables como "NULL" por conveniencia. posteriormente se le asignarán los valores correspondientes.
        x1 = null;
        x2 = null;
        parteReal = null;
        parteImaginaria = null;

        //VALIDACIONES.
        if ((cuadroTextoA.getText().length() == 0)) { //Se valida que se haya ingresado dato para el coeficiente A.
            cuadroTextoA.setError("Debe ingresar el coeficiente A"); //Mostramos error al lado de la caja de texto.

        } else {
            // Se ha ingresado dato para coeficienteA, se procede a asignarlo al atributo correspondiente.
            coeficienteA = Double.parseDouble(cuadroTextoA.getText().toString());
            if ((cuadroTextoB.getText().length() == 0)) {//Se valida que se haya ingresado dato para el coeficiente B.
                cuadroTextoB.setError("Debe ingresar el coeficiente B");//Mostramos error al lado de la caja de texto.
            } else {
                if ((cuadroTextoC.getText().length() == 0)) { //Se valida que se haya ingresado dato para el coeficiente B.
                    cuadroTextoC.setError("Debe ingresar el coeficiente C");//Mostramos error al lado de la caja de texto.
                } else {
                    if (coeficienteA == 0) { //Verificamos que el CoefienteA no sea igual a 0
                        cuadroTextoA.setError("El coeficiente A no puede ser 0");//Mostramos error al lado de la caja de texto.
                    } else {
                        //NO hay Error en la entrada de datos.
                        //PROCESO PARA RESOLVER ECUACIÓN.

                        //Asignamos a los coeficiente B y C los valores respectivos.
                        coeficienteB = Double.parseDouble(cuadroTextoB.getText().toString());
                        coeficienteC = Double.parseDouble(cuadroTextoC.getText().toString());

                        discriminante = Math.pow(coeficienteB, 2) - 4 * coeficienteA * coeficienteC; //Calculamos el discriminante.

                        //A partir del discriminante evaluamos si hay una o dos soluciones y si la solución es real o compleja.
                        if (discriminante >= 0) {
                            if (discriminante == 0) {
                                //Cuando el resultado es solo un valor para x.

                                x1 = -coeficienteB / (2 * coeficienteA);

                            } else {
                                //Cuando el resultado son dos valores de x.

                                x1 = (-coeficienteB + Math.sqrt(discriminante)) / (2 * coeficienteA);
                                x2 = (-coeficienteB - Math.sqrt(discriminante)) / (2 * coeficienteA);

                            }

                        } else {
                            //Proceso cuando se tienen números complejos.

                            discriminante = Math.abs(discriminante);
                            parteReal = -coeficienteB / (2 * coeficienteA);
                            parteImaginaria = Math.sqrt(discriminante) / (2 * coeficienteA);
                        }

                        //Creamos un objeto "Intent" para ejecutar la actividad que presenta los resultados.
                        Intent pantallaResultado = new Intent(this, Resultado_Actividad.class);
                        pantallaResultado.putExtra("A", String.valueOf(coeficienteA)); //Enviamos el coeficienteA
                        pantallaResultado.putExtra("B", String.valueOf(coeficienteB));//Enviamos el coeficienteB
                        pantallaResultado.putExtra("C", String.valueOf(coeficienteC));//Enviamos el coeficienteC

                        //Condicional para armar las cadenas que representan los resultados de la operación.
                        if (x1 != null && x2 == null) {
                            //Si solo hay un valor para x:
                            pantallaResultado.putExtra("x1", "x: " + String.valueOf(x1)); //Enviamos el valor de x1.
                            pantallaResultado.putExtra("x2", ""); //No se envia valor de x2 ya que la ecuación solo tiene un valor para x.
                        } else if (x1 != null && x2 != null) {
                            //Si la solución son números reales.
                            pantallaResultado.putExtra("x1", "x1: " + String.valueOf(x1)); //Enviamos el valor de x1.
                            pantallaResultado.putExtra("x2", "x2: " + String.valueOf(x2)); //Enviamos el valor de x2.
                        } else {
                            //Si la solución contiene números imaginarios (armamos el valor tomando en cuenta la parte real y la parte imaginaria).
                            pantallaResultado.putExtra("x1", "x1: " + String.valueOf(parteReal) + " + " + String.valueOf(parteImaginaria) + "i"); //Enviamos el valor de x1.
                            pantallaResultado.putExtra("x2", "x2: " + String.valueOf(parteReal) + " - " + String.valueOf(parteImaginaria) + "i"); //Enviamos el valor de x2.

                        }


                        startActivity(pantallaResultado); //Iniciamos la actividad que muestra los resultados.

                    }
                }
            }
        }
    }

    //Método que limpia los valores ingresados en las cajas de texto.
    public void limpiarCuadros(View vista) {
        cuadroTextoA.setText("");
        cuadroTextoB.setText("");
        cuadroTextoC.setText("");

    }


}