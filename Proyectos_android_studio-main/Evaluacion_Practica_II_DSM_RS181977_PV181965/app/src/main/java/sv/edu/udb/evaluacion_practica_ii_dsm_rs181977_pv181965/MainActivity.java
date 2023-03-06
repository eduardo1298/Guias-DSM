package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityInicioEstudianteBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityMainBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference referenciaExamen = database.getReference("Examenes");
    Query consultaOrdenada = referenciaExamen.orderByChild("nombre");
    List<Examen> Examenes;
    ListView ListaExamenes;
    private FirebaseAuth miAuth;
    private TextView NombreUsuario;
    private Button CerrarSesion;
    private FloatingActionButton BotonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        miAuth = FirebaseAuth.getInstance();
        Inicializar();
        FirebaseUser usuarioactual = FirebaseAuth.getInstance().getCurrentUser();
       NombreUsuario.setText(usuarioactual.getDisplayName());

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Inicio_sesion.class);
                startActivity(intent);
            }
        });

        BotonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent intent = new Intent(getBaseContext(), AgregarExamen.class);
                intent.putExtra("accion","agregar"); // Agregar
                intent.putExtra("key","");
                intent.putExtra("nombre","");
                intent.putExtra("codigo","");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser UsuarioActual = miAuth.getCurrentUser();
        Verificarsesion(UsuarioActual);
    }

    private void Verificarsesion(FirebaseUser usuario) {
        if (usuario == null) {
            Intent intent = new Intent(MainActivity.this, Inicio_sesion.class);
            startActivity(intent);
        }
    }

    private void Inicializar(){
        BotonAgregar=binding.BotonAgregarExamen;
        ListaExamenes = binding.ListaExamenes;
        NombreUsuario=binding.NombreUsuario;
        CerrarSesion=binding.BotonCerrarSesion;

        // Cuando el usuario haga clic en la lista (para editar registro)
        ListaExamenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                Intent intent = new Intent(getBaseContext(), GestionPreguntas.class);
                intent.putExtra("keyexamen",Examenes.get(posicion).getKey());
                intent.putExtra("nombre",Examenes.get(posicion).getNombre());
                intent.putExtra("codigo",Examenes.get(posicion).getCodigo());
                startActivity(intent);
            }
        });

        ListaExamenes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent intent = new Intent(getBaseContext(),AgregarExamen.class);
                intent.putExtra("accion","editar"); // Editar
                intent.putExtra("key", Examenes.get(posicion).getKey());
                intent.putExtra("nombre",Examenes.get(posicion).getNombre());
                intent.putExtra("codigo",Examenes.get(posicion).getCodigo());
                startActivity(intent);
                return false;
            }
        });

        BotonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent intent = new Intent(getBaseContext(), AgregarExamen.class);
                intent.putExtra("accion","agregar"); // Agregar
                intent.putExtra("key","");
                intent.putExtra("nombre","");
                intent.putExtra("codigo","");
                startActivity(intent);
            }
        });

        Examenes = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                Examenes.removeAll(Examenes);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Examen examen = dato.getValue(Examen.class);
                    examen.setKey(dato.getKey());
                    Examenes.add(examen);

                }

                AdaptadorExamenes adapter = new AdaptadorExamenes(MainActivity.this,
                        Examenes );
                ListaExamenes.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    }
