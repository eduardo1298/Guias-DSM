package sv.edu.udb.examen1.RS181977.PV181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_fracciones extends AppCompatActivity {
    //Declaramos variables
    TextView suma,resta,multiplicacion,division;
    String numerador1,numerador2,denominador1,denominador2;
    Button BotonRegresar;
    int numeradorsuma,denominadorsuma;
    int numeradorresta,denominadorresta;
    int numeradormulti,denominadormulti;
    int numeradordivi,denominadordivi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fracciones);
        //Enlazamos los componentes de la pantalla de resultados
        suma=(TextView) findViewById(R.id.Suma);
        resta=(TextView) findViewById(R.id.Resta);
        multiplicacion=(TextView) findViewById(R.id.Multiplicacion);
        division=(TextView) findViewById(R.id.Division);
        BotonRegresar=(Button)findViewById(R.id.BotonRegresar);
        //Recuperamos los datos que hemos pasado de la pantalla principal
        Bundle bundle = this.getIntent().getExtras();
        numerador1 = bundle.getString("NUMERADOR1");
        denominador1 = bundle.getString("DENOMINADOR1");
        numerador2 = bundle.getString("NUMERADOR2");
        denominador2 = bundle.getString("DENOMINADOR2");
        //Procedimiento suma de fracciones
        numeradorsuma=(Integer.parseInt(numerador1)*Integer.parseInt(denominador2))+((Integer.parseInt(denominador1)*Integer.parseInt(numerador2)));
        denominadorsuma=Integer.parseInt(denominador1)*Integer.parseInt(denominador2);
        suma.setText(numerador1+"/"+denominador1+" + "+numerador2+"/"+denominador2+" = "+simplificar(numeradorsuma,denominadorsuma));

        //Procedimiento resta de fracciones
        numeradorresta=Integer.parseInt(numerador1)*Integer.parseInt(denominador2)-Integer.parseInt(denominador1)*Integer.parseInt(numerador2);
        denominadorresta=Integer.parseInt(denominador1)*Integer.parseInt(denominador2);
        resta.setText(numerador1+"/"+denominador1+" - "+numerador2+"/"+denominador2+" = "+simplificar(numeradorresta,denominadorresta));

        //Procedimiento multiplicación de fracciones
        numeradormulti=Integer.parseInt(numerador1)*Integer.parseInt(numerador2);
        denominadormulti=Integer.parseInt(denominador1)*Integer.parseInt(denominador2);
        multiplicacion.setText(numerador1+"/"+denominador1+" * "+numerador2+"/"+denominador2+" = "+simplificar(numeradormulti,denominadormulti));

        //Procedimiento división de fracciones
        numeradordivi=Integer.parseInt(numerador1)*Integer.parseInt(denominador2);
        denominadordivi=Integer.parseInt(denominador1)*Integer.parseInt(numerador2);
        division.setText("("+numerador1+"/"+denominador1+")"+" / "+"("+numerador2+"/"+denominador2+")"+" = "+simplificar(numeradordivi,denominadordivi));

        //Al presionar el botón regresar, volveremos a la pantalla principal.
        BotonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(activity_fracciones.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

//Función para obtener el mínimo común múltiplo
    int mcd(int numerador,int denominador){
        int n=Math.abs(numerador);
        int d=Math.abs(denominador);
        int residuo;
        while(d!=0){
            residuo=n%d;
            n=d;
            d=residuo;
        }
        return n;
    }

    //método para simplificar el resultado de la fracción
    private String simplificar(int numerador,int denominador) {
        int n = mcd(numerador,denominador); //se calcula el mcd de la fracción
        numerador = numerador / n;
        denominador = denominador / n;
        if(numerador==denominador) {
            return numerador + "/" + denominador + " = " + "1";
        }else if(numerador==0) {
            return numerador + "/" + denominador + " = " + "0";
        }else if(denominador==1){
            return numerador + "/" + denominador + " = " + numerador/denominador;
        }else{
            return numerador + "/" + denominador;
        }

    }



}