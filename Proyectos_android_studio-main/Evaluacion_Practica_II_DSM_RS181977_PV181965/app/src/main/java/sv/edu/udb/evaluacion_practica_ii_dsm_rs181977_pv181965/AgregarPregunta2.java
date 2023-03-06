package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarExamenBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarPregunta2Binding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityInicioSesionBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class AgregarPregunta2 extends AppCompatActivity {
    private ActivityAgregarPregunta2Binding binding;
    private EditText Preguntatexto,opcion1texto,opcion2texto,opcion3texto;
    private Button BotonGuardarPregunta;
    private ImageView BotonRegresar;
    private RadioButton Radioopcion1, Radioopcion2,Radioopcion3;
    private RadioGroup ContenedorRadio;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    String RespuestaObtenidaRadio;
    String key = "", pregunta = "", opcion1 = "", opcion2 = "",opcion3="", respuesta = "", accion = "", keyexamen = "";
    String nombre="",codigo="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarPregunta2Binding.inflate(getLayoutInflater());
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
    void Inicializar(){
    Preguntatexto=binding.Pregunta;
    BotonGuardarPregunta=binding.botonCrearPregunta;
    BotonRegresar=binding.botonRegresar;
    Radioopcion1=binding.radioopcion1;
    Radioopcion2=binding.radioopcion2;
    Radioopcion3=binding.radioopcion3;
    ContenedorRadio=binding.ContenedorRadio;
    opcion1texto=binding.Opcion1texto;
    opcion2texto=binding.Opcion2texto;
    opcion3texto=binding.Opcion3texto;

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        keyexamen = datos.getString("keyexamen");
        key = datos.getString("key");
        nombre = datos.getString("nombre");
        codigo = datos.getString("codigo");
        pregunta = datos.getString("pregunta");
        opcion1 = datos.getString("opcion1");
        opcion2 = datos.getString("opcion2");
        opcion3 = datos.getString("opcion3");
        respuesta = datos.getString("respuesta");
        accion = datos.getString("accion");
        Preguntatexto.setText(pregunta);
        opcion1texto.setText(opcion1);
        opcion2texto.setText(opcion2);
        opcion3texto.setText(opcion3);

        if("".equals(respuesta)){
        }else{
            if(respuesta.equals(opcion1texto.getText().toString())){
                Radioopcion1.setChecked(true);
            }else if(respuesta.equals(opcion2texto.getText().toString())){
                Radioopcion2.setChecked(true);
            }else if(respuesta.equals(opcion3texto.getText().toString())){
                Radioopcion3.setChecked(true);
            }
        }

        BotonGuardarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference referenciaPreguntas = database.getReference("Examenes").child(keyexamen).child("Preguntas");
                String Pregunta = Preguntatexto.getText().toString().trim();
                String opcion1 = opcion1texto.getText().toString().trim();
                String opcion2 = opcion2texto.getText().toString().trim();
                String opcion3 = opcion3texto.getText().toString().trim();
                Preguntatexto.setError(null);
                opcion1texto.setError(null);
                opcion2texto.setError(null);
                opcion3texto.setError(null);
                if ("".equals(Pregunta)) {
                    Preguntatexto.setError("Error.Ingresa el campo pregunta");
                    Preguntatexto.requestFocus();
                    return;
                }else if("".equals(opcion1)){
                    opcion1texto.setError("Error.Ingresa el campo opcion 1");
                    opcion1texto.requestFocus();
                    return;
                }else if("".equals(opcion2)){
                    opcion2texto.setError("Error.Ingresa el campo opcion 2");
                    opcion2texto.requestFocus();
                    return;
                }else if("".equals(opcion3)){
                    opcion3texto.setError("Error.Ingresa el campo opcion 3");
                    opcion3texto.requestFocus();
                    return;
                }


                if (Radioopcion1.isChecked() == true) {
                    RespuestaObtenidaRadio = opcion1texto.getText().toString();
                } else if(Radioopcion2.isChecked() == true) {
                    RespuestaObtenidaRadio = opcion2texto.getText().toString();
                }else if(Radioopcion3.isChecked() == true){
                    RespuestaObtenidaRadio = opcion3texto.getText().toString();
                }

                // Se forma objeto pregunta
                Pregunta pregunta = new Pregunta(Pregunta, opcion1, opcion2, opcion3, RespuestaObtenidaRadio, "multiple");

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