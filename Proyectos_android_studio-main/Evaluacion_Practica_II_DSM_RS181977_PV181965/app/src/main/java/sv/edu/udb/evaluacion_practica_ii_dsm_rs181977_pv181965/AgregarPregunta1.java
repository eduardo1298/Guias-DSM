package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarExamenBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarPregunta1Binding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarPregunta2Binding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class AgregarPregunta1 extends AppCompatActivity {
    private ActivityAgregarPregunta1Binding binding;
    private EditText Preguntatexto;
    private Button BotonGuardarPregunta;
    private ImageView BotonRegresar;
    private RadioButton RadioVerdadero, RadioFalso;
    private RadioGroup ContenedorRadio;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    String RespuestaObtenidaRadio;
    String key = "", pregunta = "", opcion1 = "", opcion2 = "", respuesta = "", accion = "", keyexamen = "";
    String nombre="",codigo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarPregunta1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Inicializar();

        BotonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GestionPreguntas.class);
                intent.putExtra("keyexamen",keyexamen);
                intent.putExtra("nombre",nombre);
                intent.putExtra("codigo",codigo);
                startActivity(intent);
            }
        });
    }

    void Inicializar() {
        BotonGuardarPregunta = binding.botonCrearPregunta;
        RadioVerdadero = binding.RadioVerdadero;
        RadioFalso = binding.RadioFalso;
        BotonRegresar = binding.botonRegresar;
        ContenedorRadio = binding.ContenedorRadio;
        Preguntatexto = binding.Pregunta;

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        keyexamen = datos.getString("keyexamen");
        key = datos.getString("key");
        nombre = datos.getString("nombre");
        codigo = datos.getString("codigo");
        pregunta = datos.getString("pregunta");
        opcion1 = datos.getString("opcion1");
        opcion2 = datos.getString("opcion2");
        respuesta = datos.getString("respuesta");
        accion = datos.getString("accion");
        Preguntatexto.setText(pregunta);
       if("".equals(respuesta)){
        }else{
            if(respuesta.equals(RadioFalso.getText().toString())){
                RadioFalso.setChecked(true);
            }else{
                RadioVerdadero.setChecked(true);
            }
        }
        BotonGuardarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference referenciaPreguntas = database.getReference("Examenes").child(keyexamen).child("Preguntas");
                String Pregunta = Preguntatexto.getText().toString().trim();
                String opcion1 = RadioVerdadero.getText().toString();
                String opcion2 = RadioFalso.getText().toString();
                Preguntatexto.setError(null);
                if ("".equals(Pregunta)) {
                    Preguntatexto.setError("Error.Ingresa el campo pregunta");
                    Preguntatexto.requestFocus();
                    return;
                }
                if (RadioFalso.isChecked() == true) {
                    RespuestaObtenidaRadio = RadioFalso.getText().toString();
                } else {
                    RespuestaObtenidaRadio = RadioVerdadero.getText().toString();
                }

                // Se forma objeto persona
                Pregunta pregunta = new Pregunta(Pregunta, opcion1, opcion2, " ", RespuestaObtenidaRadio, "2opciones");

                if (accion.equals("agregar")) { //Agregar usando push()
                    referenciaPreguntas.push().setValue(pregunta);
                } else // Editar usando setValue
                {
                    referenciaPreguntas.child(key).setValue(pregunta);
                }
                finish();
            }
        });

    }


}