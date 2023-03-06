package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityCuestionarioExamenBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityMainBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.CuestionarioLayoutBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ExamenLayoutBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class CuestionarioExamen extends AppCompatActivity {


    private ActivityCuestionarioExamenBinding binding;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    private final List<Pregunta> Preguntas = new ArrayList<>();
    private int indicepregunta = 0;

    private FirebaseAuth miAuth;
    private TextView codigoDeExamen, NombreDeExamen;
    private Button botonTerminarExamen;

    private RadioButton radioOpcion1;
    private RadioButton radioOpcion2;
    private RadioButton radioOpcion3;

    private TextView tituloPregunta;
    private RadioGroup rbGrupo;
    private TextView cajaCantidadPreguntas;
    private String codigoExamen,nombreExamen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCuestionarioExamenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);
        miAuth = FirebaseAuth.getInstance();
        botonTerminarExamen = binding.BotonTerminar;
        radioOpcion1 = binding.radioopcion1;
        radioOpcion2 = binding.radioopcion2;
        radioOpcion3 = binding.radioopcion3;
        tituloPregunta = binding.tituloPregunta;
        rbGrupo = binding.ContenedorRadio;
        cajaCantidadPreguntas = binding.cantidadPreguntas;
        Inicializar();
        FirebaseUser usuarioactual = FirebaseAuth.getInstance().getCurrentUser();


        // Collections.shuffle(Preguntas);

        // cantidadDePreguntas = Preguntas.size();
        // cajaCantidadPreguntas.setText(String.valueOf(cantidadDePreguntas));

        //mostrarSiguientePregunta();


        //Si el usuario hace click en el botón para acabar examen (o para avanzar en las preguntas).
        botonTerminarExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  if ((indicepregunta+1) == Preguntas.size()) {
               //               botonTerminarExamen.setText("Finalizar");
               //          }

                String RespuestaSeleccionada = "";

                if ("multiple".equals(Preguntas.get(indicepregunta).getTipo())) {
                    if (!radioOpcion1.isChecked() && !radioOpcion2.isChecked() && !radioOpcion3.isChecked()) {
                        Toast.makeText(CuestionarioExamen.this, "Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rbGrupo.getCheckedRadioButtonId() == binding.radioopcion1.getId()) {
                            RespuestaSeleccionada = radioOpcion1.getText().toString();
                        } else if (rbGrupo.getCheckedRadioButtonId() == binding.radioopcion2.getId()) {
                            RespuestaSeleccionada = radioOpcion2.getText().toString();
                        } else if (rbGrupo.getCheckedRadioButtonId() == binding.radioopcion3.getId()) {
                            RespuestaSeleccionada = radioOpcion3.getText().toString();
                        }
                        Preguntas.get(indicepregunta).setRespuestaSeleccionada(RespuestaSeleccionada);
                        Log.w("Respuesta", Preguntas.get(indicepregunta).getRespuestaSeleccionada());
                        if ("Finalizar".equals(botonTerminarExamen.getText().toString())) {
                            Intent intent = new Intent(getBaseContext(), ResultadoCuestionario.class);
                            intent.putExtra("nombreexamen",nombreExamen);
                            intent.putExtra("numeropreguntas",String.valueOf(Preguntas.size()));
                            intent.putExtra("numerocorrectas",String.valueOf(ObtenerRespuestasCorrectas()));
                            intent.putExtra("numeroincorrectas",String.valueOf(ObtenerRespuestasIncorrectas()));
                            intent.putExtra("listapregunta", (Serializable) Preguntas);
                            startActivity(intent);
                            finish();
                        } else {
                            indicepregunta++;
                            if ("multiple".equals(Preguntas.get(indicepregunta).getTipo())) {
                                tituloPregunta.setText(Preguntas.get(indicepregunta).getPregunta());
                                radioOpcion1.setText(Preguntas.get(indicepregunta).getOpcion1());
                                radioOpcion2.setText(Preguntas.get(indicepregunta).getOpcion2());
                                radioOpcion3.setText(Preguntas.get(indicepregunta).getOpcion3());
                                radioOpcion3.setVisibility(View.VISIBLE);
                                cajaCantidadPreguntas.setText("Pregunta # " + (indicepregunta + 1) + " de " + Preguntas.size());
                                rbGrupo.clearCheck();
                            } else {
                                tituloPregunta.setText(Preguntas.get(indicepregunta).getPregunta());
                                radioOpcion1.setText(Preguntas.get(indicepregunta).getOpcion1());
                                radioOpcion2.setText(Preguntas.get(indicepregunta).getOpcion2());
                                radioOpcion3.setVisibility(View.GONE);
                                cajaCantidadPreguntas.setText("Pregunta # " + (indicepregunta + 1) + " de " + Preguntas.size());
                                rbGrupo.clearCheck();
                            }
                        }

                    }
                } else {
                    if (!radioOpcion1.isChecked() && !radioOpcion2.isChecked()) {
                        Toast.makeText(CuestionarioExamen.this, "Debe seleccionar una opción", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rbGrupo.getCheckedRadioButtonId() == binding.radioopcion1.getId()) {
                            RespuestaSeleccionada = radioOpcion1.getText().toString();
                        } else if (rbGrupo.getCheckedRadioButtonId() == binding.radioopcion2.getId()) {
                            RespuestaSeleccionada = radioOpcion2.getText().toString();
                        }
                        Preguntas.get(indicepregunta).setRespuestaSeleccionada(RespuestaSeleccionada);
                        Log.w("Respuesta", Preguntas.get(0).getRespuestaSeleccionada());

                        if ("Finalizar".equals(botonTerminarExamen.getText().toString())) {
                            Intent intent = new Intent(getBaseContext(), ResultadoCuestionario.class);
                            intent.putExtra("nombreexamen",nombreExamen);
                            intent.putExtra("numeropreguntas",String.valueOf(Preguntas.size()));
                            intent.putExtra("numerocorrectas",String.valueOf(ObtenerRespuestasCorrectas()));
                            intent.putExtra("numeroincorrectas",String.valueOf(ObtenerRespuestasIncorrectas()));
                            intent.putExtra("listapregunta", (Serializable) Preguntas);
                            startActivity(intent);
                            finish();
                        } else {
                            indicepregunta++;
                            if ("multiple".equals(Preguntas.get(indicepregunta).getTipo())) {
                                tituloPregunta.setText(Preguntas.get(indicepregunta).getPregunta());
                                radioOpcion1.setText(Preguntas.get(indicepregunta).getOpcion1());
                                radioOpcion2.setText(Preguntas.get(indicepregunta).getOpcion2());
                                radioOpcion3.setText(Preguntas.get(indicepregunta).getOpcion3());
                                radioOpcion3.setVisibility(View.VISIBLE);
                                cajaCantidadPreguntas.setText("Pregunta # " + (indicepregunta + 1) + " de " + Preguntas.size());
                                rbGrupo.clearCheck();
                            } else {
                                tituloPregunta.setText(Preguntas.get(indicepregunta).getPregunta());
                                radioOpcion1.setText(Preguntas.get(indicepregunta).getOpcion1());
                                radioOpcion2.setText(Preguntas.get(indicepregunta).getOpcion2());
                                radioOpcion3.setVisibility(View.GONE);
                                cajaCantidadPreguntas.setText("Pregunta # " + (indicepregunta + 1) + " de " + Preguntas.size());
                                rbGrupo.clearCheck();
                            }
                        }

                    }
                }


                if ((indicepregunta+1) == Preguntas.size()) {
                    botonTerminarExamen.setText("Finalizar");
                }

            }
        });


    }


    private void Inicializar() {

        // ListaCuestionario = binding.ListaCuestionario;

        //Obtenemos Código de examen.
        Bundle datos = getIntent().getExtras();
        codigoExamen = datos.getString("codigoExamen");
        nombreExamen = datos.getString("nombreExamen");

        Examen objetoExamen = new Examen(nombreExamen, codigoExamen);

        //Registramos el código del examen en los "códigos" del estudiante.
        String usuarioActual = miAuth.getCurrentUser().getUid();
        DatabaseReference referenciaCodigo = database.getReference("CodigoExamen").child(usuarioActual).child("Codigos");
        referenciaCodigo.child(objetoExamen.getCodigo()).setValue(objetoExamen);


        DatabaseReference referenciaExamen = database.getReference("Examenes").child(codigoExamen).child("Preguntas");
        Query consultaOrdenada = referenciaExamen.orderByChild("Preguntas");
        NombreDeExamen = binding.NombreExamen;
        NombreDeExamen.setText(nombreExamen);
        codigoDeExamen = binding.codigoDeExamen;
        codigoDeExamen.setText("Código: " + codigoExamen); //Texto en pantalla: se muestra el código del examen.


        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                Preguntas.removeAll(Preguntas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Pregunta pregunta = dato.getValue(Pregunta.class);
                    Preguntas.add(pregunta);
                }

                if(Preguntas.size()==0){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Toast.makeText(CuestionarioExamen.this,"El examen aún no tiene preguntas, intente más tarde.",Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    if(Preguntas.size()==1){
                        botonTerminarExamen.setText("Finalizar");
                    }

                    String tipo = Preguntas.get(indicepregunta).getTipo();
                    if ("multiple".equals(Preguntas.get(indicepregunta).getTipo())) {
                        tituloPregunta.setText(Preguntas.get(indicepregunta).getPregunta());
                        radioOpcion1.setText(Preguntas.get(indicepregunta).getOpcion1());
                        radioOpcion2.setText(Preguntas.get(indicepregunta).getOpcion2());
                        radioOpcion3.setText(Preguntas.get(indicepregunta).getOpcion3());
                        radioOpcion3.setVisibility(View.VISIBLE);
                    } else {
                        tituloPregunta.setText(Preguntas.get(indicepregunta).getPregunta());
                        radioOpcion1.setText(Preguntas.get(indicepregunta).getOpcion1());
                        radioOpcion2.setText(Preguntas.get(indicepregunta).getOpcion2());
                        radioOpcion3.setVisibility(View.GONE);

                    }
                    cajaCantidadPreguntas.setText("Pregunta # " + (indicepregunta + 1) + " de " + Preguntas.size());
                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        });


    }


    public int ObtenerRespuestasCorrectas() {
        int respuestascorrectas = 0;
        for (int indice = 0; indice <= (Preguntas.size()-1); indice++) {
            final String RespuestaSeleccionada = Preguntas.get(indice).getRespuestaSeleccionada();
            final String RespuestaCorrecta = Preguntas.get(indice).getRespuestaCorrecta();
            if (RespuestaSeleccionada.equals(RespuestaCorrecta)) {
                respuestascorrectas++;
            }
        }
        return respuestascorrectas;
    }

    public int ObtenerRespuestasIncorrectas() {
        int respuestasincorrectas = 0;
        for (int indice = 0; indice <= Preguntas.size()-1; indice++) {
            final String RespuestaSeleccionada = Preguntas.get(indice).getRespuestaSeleccionada();
            final String RespuestaCorrecta = Preguntas.get(indice).getRespuestaCorrecta();
            if (!RespuestaSeleccionada.equals(RespuestaCorrecta)) {
                respuestasincorrectas++;
            }
        }
        return respuestasincorrectas;
    }


}