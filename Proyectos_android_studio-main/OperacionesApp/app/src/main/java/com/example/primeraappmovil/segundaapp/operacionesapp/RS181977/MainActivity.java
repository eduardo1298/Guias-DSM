package com.example.primeraappmovil.segundaapp.operacionesapp.RS181977;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        tv1=findViewById(R.id.tv1);

    }
    //Este método se ejecutará cuando se presione el botón sumar
    public void sumar(View view) {
        et1.setError(null);
        et2.setError(null);
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        if ("".equals(valor1)) {
            et1.setError("Introduce un número");
            et1.requestFocus();
            return;
        }else if("".equals(valor2)){
            et2.setError("Introduce un número");
            et2.requestFocus();
            return;
        }
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);
        int suma=nro1+nro2;
        String resu=String.valueOf(suma);
        tv1.setText(resu);
    }

    //Este método se ejecutará cuando se presione el botón restar
    public void restar(View view) {
        et1.setError(null);
        et2.setError(null);
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        if ("".equals(valor1)) {
            et1.setError("Introduce un número");
            et1.requestFocus();
            return;
        }else if("".equals(valor2)){
            et2.setError("Introduce un número");
            et2.requestFocus();
            return;
        }
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);
        int resta=nro1-nro2;
        String resu=String.valueOf(resta);
        tv1.setText(resu);
    }

    //Este método se ejecutará cuando se presione el botón multiplicar
    public void multiplicar(View view) {
        et1.setError(null);
        et2.setError(null);
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        if ("".equals(valor1)) {
            et1.setError("Introduce un número");
            et1.requestFocus();
            return;
        }else if("".equals(valor2)){
            et2.setError("Introduce un número");
            et2.requestFocus();
            return;
        }
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);
        int multi=nro1*nro2;
        String resu=String.valueOf(multi);
        tv1.setText(resu);
    }

    //Este método se ejecutará cuando se presione el botón dividir
    public void dividir(View view) {
        et1.setError(null);
        et2.setError(null);
        String valor1=et1.getText().toString();
        String valor2=et2.getText().toString();
        if ("".equals(valor1)) {
            et1.setError("Introduce un número");
            et1.requestFocus();
            return;
        }else if("".equals(valor2)){
            et2.setError("Introduce un número");
            et2.requestFocus();
            return;
        }
        float nro1=Float.parseFloat(valor1);
        float nro2=Float.parseFloat(valor2);
        if(nro2==0){
            String resu="No se puede dividir entre 0";
            tv1.setText(resu);
        }else{
            float division=nro1/nro2;
            String resu=String.valueOf(division);
            tv1.setText(resu);
        }

    }
}