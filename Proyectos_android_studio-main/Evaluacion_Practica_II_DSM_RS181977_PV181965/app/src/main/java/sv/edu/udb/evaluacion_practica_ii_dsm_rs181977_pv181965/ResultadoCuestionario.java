package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarPregunta2Binding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityResultadoCuestionarioBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class ResultadoCuestionario extends AppCompatActivity {
    private ActivityResultadoCuestionarioBinding binding;
    private TextView tvTituloResultado,tvNota,tvRespuestasIncorrectas,tvRespuestasCorrectas,tvNumeroPreguntas;
    private Button BotonRegresarInicio;
    private ListView ListaSolucion;
    private ArrayList<Pregunta> Preguntas;
    String nombreExamen,NumeroCorrectas,NumeroIncorrectas,NumeroPreguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultadoCuestionarioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Inicializar();

        BotonRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoCuestionario.this, InicioEstudiante.class);
                startActivity(intent);
            }
        });
    }

    void Inicializar(){
        tvTituloResultado=binding.titulo;
        tvNota=binding.tvnota;
        tvRespuestasCorrectas=binding.tvcorrectas;
        tvRespuestasIncorrectas=binding.tvincorrectas;
        tvNumeroPreguntas=binding.tvpreguntas;
        BotonRegresarInicio=binding.botonRegresarInicio;
        ListaSolucion=binding.Listasolucion;
        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        nombreExamen = datos.getString("nombreexamen");
        NumeroPreguntas= datos.getString("numeropreguntas");
        NumeroCorrectas= datos.getString("numerocorrectas");
        NumeroIncorrectas = datos.getString("numeroincorrectas");
        ArrayList<Pregunta> Preguntas = (ArrayList<Pregunta> ) getIntent().getSerializableExtra("listapregunta");

        //Dar formato a los número decimales
        DecimalFormat formato = new DecimalFormat();
        formato.setMaximumFractionDigits(2); //Numero maximo de decimales a mostrar

        float nota=(Float.parseFloat(NumeroCorrectas)/Float.parseFloat(NumeroPreguntas))*10;



       // tvTituloResultado.setText("Examen: " + nombreExamen);
        tvTituloResultado.setVisibility(View.GONE);
        tvNumeroPreguntas.setText("Número total de preguntas : "+NumeroPreguntas);
        tvRespuestasCorrectas.setText("Ha tenido : " +NumeroCorrectas + " Respuestas correctas");
        tvRespuestasIncorrectas.setText("Ha tenido : " +NumeroIncorrectas + " Respuestas incorrectas");
        tvNota.setText("Su nota es : "+formato.format(nota));

        AdaptadorCuestionario adapter = new AdaptadorCuestionario(ResultadoCuestionario.this,
                Preguntas);
        ListaSolucion.setAdapter(adapter);


    }
}