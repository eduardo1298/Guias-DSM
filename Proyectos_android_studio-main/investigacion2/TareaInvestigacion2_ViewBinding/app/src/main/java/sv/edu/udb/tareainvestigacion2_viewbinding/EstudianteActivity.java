package sv.edu.udb.tareainvestigacion2_viewbinding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.tareainvestigacion2_viewbinding.databinding.ActivityAgregarEstudianteBinding;
import sv.edu.udb.tareainvestigacion2_viewbinding.databinding.ActivityEstudianteBinding;
import sv.edu.udb.tareainvestigacion2_viewbinding.databinding.EstudianteLayoutBinding;
import sv.edu.udb.tareainvestigacion2_viewbinding.datos.Estudiante;


public class EstudianteActivity extends AppCompatActivity {
    private ActivityEstudianteBinding binding;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refEstudiante = database.getReference("Estudiantes");
    // Ordenamiento
    Query consultaOrdenada = refEstudiante.orderByChild("nombres");

    List<Estudiante> estudiantes;
    ListView listaEstudiantes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEstudianteBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);
        inicializar();
    }
    private void inicializar() {
        listaEstudiantes = binding.ListaEstudiantes;

        // Cuando el usuario haga clic en la lista (para editar registro)
        listaEstudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent intent = new Intent(getBaseContext(), AgregarEstudianteActivity.class);

                intent.putExtra("accion","editar"); // Editar
                intent.putExtra("key", estudiantes.get(posicion).getKey());
                intent.putExtra("nombres",estudiantes.get(posicion).getNombres());
                intent.putExtra("apellidos",estudiantes.get(posicion).getApellidos());
                intent.putExtra("carnet",estudiantes.get(posicion).getCarnet());
                intent.putExtra("programaestudio",estudiantes.get(posicion).getProgramaEstudio());
                intent.putExtra("correo",estudiantes.get(posicion).getCorreo());
                intent.putExtra("telefono",estudiantes.get(posicion).getTelefono());

                startActivity(intent);
            }
        });

        // Cuando el usuario hace un LongClic (clic sin soltar elemento por mas de 2 segundos)
        // Es por que el usuario quiere eliminar el registro
        listaEstudiantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(EstudianteActivity.this);
                ad.setMessage("Está seguro de eliminar registro?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EstudianteActivity.refEstudiante
                                .child(estudiantes.get(position).getKey()).removeValue();

                        Toast.makeText(EstudianteActivity.this,
                                "Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(EstudianteActivity.this,
                                "Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });

        binding.BotonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent intent = new Intent(getBaseContext(), AgregarEstudianteActivity.class);
                intent.putExtra("accion","agregar"); // Agregar
                intent.putExtra("key","");
                intent.putExtra("nombres","");
                intent.putExtra("apellidos","");
                intent.putExtra("carnet","");
                intent.putExtra("programaestudio","");
                intent.putExtra("correo","");
                intent.putExtra("telefono","");

                startActivity(intent);
            }
        });

        estudiantes = new ArrayList<>();

        // Cambiarlo refEstudiante a consultaOrdenada para ordenar lista

        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                estudiantes.removeAll(estudiantes);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Estudiante estudiante = dato.getValue(Estudiante.class);
                    estudiante.setKey(dato.getKey());
                    estudiantes.add(estudiante);
                }

                AdaptadorEstudiante adapter = new AdaptadorEstudiante( estudiantes,EstudianteActivity.this);
                listaEstudiantes.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


}