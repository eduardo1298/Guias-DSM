package sv.edu.udb.rs181977.guia08app.RS181977;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.udb.rs181977.guia08app.RS181977.datos.Persona;

public class AddPersonaActivity extends AppCompatActivity {
    EditText edtDUI, edtNombre,edtFecha,edtPeso,edtAltura;
    String key="",nombre="",dui="",accion="",fecha="",peso="",altura="",genero="";
    Spinner SpGenero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);
        inicializar();
    }

    private void inicializar() {
        edtNombre = findViewById(R.id.edtNombre);
        edtDUI = findViewById(R.id.edtDUI);
        edtFecha = findViewById(R.id.edtfecha);
        edtPeso = findViewById(R.id.edtpeso);
        edtAltura = findViewById(R.id.edtaltura);
         SpGenero = findViewById(R.id.SpinnerGenero);


        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        dui = datos.getString("dui");
        nombre=datos.getString("nombre");
        fecha=datos.getString("fecha");
        altura=datos.getString("altura");
        peso=datos.getString("peso");
        genero=datos.getString("genero");
        accion=datos.getString("accion");
        edtDUI.setText(dui);
        edtNombre.setText(nombre);
        edtFecha.setText(fecha);
        edtPeso.setText(peso);
        edtAltura.setText(altura);
        if(genero.equals("Hombre")){
            SpGenero.setSelection(1);
        }else if(genero.equals("Mujer")){
            SpGenero.setSelection(2);
        }
    }

    public void guardar(View v){
        String nombre = edtNombre.getText().toString();
        String dui = edtDUI.getText().toString();
        String fecha = edtFecha.getText().toString();
        String peso = edtPeso.getText().toString();
        String altura = edtAltura.getText().toString();
        String genero = SpGenero.getSelectedItem().toString();
        edtNombre.setError(null);
        edtDUI.setError(null);
        edtFecha.setError(null);
        edtPeso.setError(null);
        edtAltura.setError(null);
        final String expreDUI = "^[0-9]{8}-[0-9]{1}$";
        final String expreFecha = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$";
        if ("".equals(edtNombre.getText().toString().trim())) {
            edtNombre.setError("Error.Ingresa el campo nombre");
            edtNombre.requestFocus();
            return;
        }else if("".equals(edtDUI.getText().toString().trim())){
            edtDUI.setError("Error.Ingresa el campo DUI");
            edtDUI.requestFocus();
            return;
        }else if(edtDUI.getText().toString().matches(expreDUI)==false){
            edtDUI.setError("Error.Formato inválido,ejemplo de formato 12345678-9");
            edtDUI.requestFocus();
            return;
        }else if("".equals(edtFecha.getText().toString().trim())){
            edtFecha.setError("Error.Ingresa el campo fecha de nacimiento");
            edtFecha.requestFocus();
            return;
        }else if(edtFecha.getText().toString().matches(expreFecha)==false){
            edtFecha.setError("Error.Formato inválido,ejemplo de formato 01/09/1976");
            edtFecha.requestFocus();
            return;
        }else if(SpGenero.getSelectedItemPosition()==0){
            Toast.makeText(getApplicationContext(),"Error, Seleccione un género",Toast.LENGTH_SHORT).show();
            SpGenero.requestFocus();
            return;
        }else if("".equals(edtPeso.getText().toString().trim())){
            edtPeso.setError("Error.Ingresa el campo peso");
            edtPeso.requestFocus();
            return;
        }else if("".equals(edtAltura.getText().toString().trim())){
            edtAltura.setError("Error.Ingresa el campo altura");
            edtAltura.requestFocus();
            return;
        }


        // Se forma objeto persona
        Persona persona = new Persona(dui,nombre,fecha,peso,altura,genero);

        if (accion.equals("a")) { //Agregar usando push()
            PersonasActivity.refPersonas.push().setValue(persona);
        }
        else // Editar usando setValue
        {
            PersonasActivity.refPersonas.child(key).setValue(persona);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }

}