package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarPregunta1Binding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarPregunta2Binding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityGestionPreguntasBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class GestionPreguntas extends AppCompatActivity {
    private ActivityGestionPreguntasBinding binding;
    private TextView NombreExamen, CodigoExamen;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference referenciaExamen = database.getReference("Examenes");
    private Button BotonPreguntaMultiple, BotonPregunta2opciones, BotonEliminarExamen;
    private ImageView BotonRegresar;
    List<Pregunta> preguntas;
    ListView ListaPreguntas;
    String keyexamen = "", nombre = "", codigo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGestionPreguntasBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Inicializar();

        BotonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GestionPreguntas.this, MainActivity.class);
                startActivity(intent);
            }
        });

        BotonPregunta2opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AgregarPregunta1.class);
                intent.putExtra("keyexamen", keyexamen);
                intent.putExtra("nombre", nombre);
                intent.putExtra("codigo", codigo);
                intent.putExtra("accion", "agregar");
                intent.putExtra("respuesta", "");

                startActivity(intent);

            }
        });

        BotonPreguntaMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AgregarPregunta2.class);
                intent.putExtra("keyexamen", keyexamen);
                intent.putExtra("nombre", nombre);
                intent.putExtra("codigo", codigo);
                intent.putExtra("accion", "agregar");
                intent.putExtra("respuesta", "");
                intent.putExtra("opcion1", "");
                intent.putExtra("opcion2", "");
                intent.putExtra("opcion3", "");

                startActivity(intent);
            }
        });

        ListaPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                if("multiple".equals(preguntas.get(posicion).getTipo())){
                    Intent intent = new Intent(getBaseContext(), AgregarPregunta2.class);
                    intent.putExtra("accion","editar"); // Editar
                    intent.putExtra("key", preguntas.get(posicion).getKey());
                    intent.putExtra("pregunta",preguntas.get(posicion).getPregunta());
                    intent.putExtra("respuesta",preguntas.get(posicion).getRespuestaCorrecta());
                    intent.putExtra("opcion1",preguntas.get(posicion).getOpcion1());
                    intent.putExtra("opcion2",preguntas.get(posicion).getOpcion2());
                    intent.putExtra("opcion3",preguntas.get(posicion).getOpcion3());
                    intent.putExtra("codigo",codigo);
                    intent.putExtra("nombre",nombre);
                    intent.putExtra("keyexamen",keyexamen);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getBaseContext(), AgregarPregunta1.class);

                    intent.putExtra("accion","editar"); // Editar
                    intent.putExtra("key", preguntas.get(posicion).getKey());
                    intent.putExtra("pregunta",preguntas.get(posicion).getPregunta());
                    intent.putExtra("respuesta",preguntas.get(posicion).getRespuestaCorrecta());
                    intent.putExtra("opcion1",preguntas.get(posicion).getOpcion1());
                    intent.putExtra("opcion2",preguntas.get(posicion).getOpcion2());
                    intent.putExtra("codigo",codigo);
                    intent.putExtra("nombre",nombre);
                    intent.putExtra("keyexamen",keyexamen);
                    startActivity(intent);
                }

            }
        });

        ListaPreguntas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(GestionPreguntas.this);
                ad.setMessage("¿Está seguro de eliminar la pregunta?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DatabaseReference referenciaPreguntas = database.getReference("Examenes").child(keyexamen).child("Preguntas");
                        referenciaPreguntas.child(preguntas.get(position).getKey()).removeValue();

                        Toast.makeText(GestionPreguntas.this,
                                "Pregunta eliminada!", Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(GestionPreguntas.this,
                                "Operación de borrado cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });

        BotonEliminarExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(GestionPreguntas.this);
                ad.setMessage("¿Está seguro de eliminar el examen?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GestionPreguntas.referenciaExamen
                                .child(keyexamen).removeValue();
                        Toast.makeText(GestionPreguntas.this,
                                "Examen borrado!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GestionPreguntas.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(GestionPreguntas.this,
                                "Operación de borrado cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
            }
        });
    }


    void Inicializar() {
        NombreExamen = binding.NombreExamen;
        CodigoExamen = binding.CodigoExamen;
        BotonPreguntaMultiple = binding.BotonAgregarPreguntaMultiple;
        BotonPregunta2opciones = binding.botonPreguntaverdadofalso;
        BotonEliminarExamen = binding.botonEliminar;
        BotonRegresar = binding.botonRegresar;
        ListaPreguntas = binding.ListaPreguntas;

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        keyexamen = datos.getString("keyexamen");
        codigo = datos.getString("codigo");
        nombre = datos.getString("nombre");
        NombreExamen.setText("Examen:" + " " + nombre);
        CodigoExamen.setText("Código de acceso:" + " " + codigo);

        preguntas = new ArrayList<>();
        DatabaseReference referenciaPreguntas = database.getReference("Examenes").child(keyexamen).child("Preguntas");
        Query consultaOrdenada = referenciaPreguntas.orderByChild("Preguntas");

        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                preguntas.removeAll(preguntas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Pregunta pregunta = dato.getValue(Pregunta.class);
                    pregunta.setKey(dato.getKey());
                    preguntas.add(pregunta);

                }
                AdaptadorPreguntas adapter = new AdaptadorPreguntas(GestionPreguntas.this,
                        preguntas);
                ListaPreguntas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}