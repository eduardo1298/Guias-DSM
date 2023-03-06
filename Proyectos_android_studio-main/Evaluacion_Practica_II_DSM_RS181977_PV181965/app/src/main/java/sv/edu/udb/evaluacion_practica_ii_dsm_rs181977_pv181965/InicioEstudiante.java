package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityInicioEstudianteBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Usuario;


public class InicioEstudiante extends AppCompatActivity {
    private ActivityInicioEstudianteBinding binding;
    private TextView UsuarioEstudiante;
    private FirebaseAuth miAuth;
    private EditText codigo;
    private Button BotonIngresarExamen,BotonHistorial;
    private Button Cerrarsesion;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    List<Examen> examenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioEstudianteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        miAuth = FirebaseAuth.getInstance();
        Inicializar();
        FirebaseUser usuarioactual = FirebaseAuth.getInstance().getCurrentUser();
       UsuarioEstudiante.setText(usuarioactual.getDisplayName());


       //Lo que se ejecutará cuando el usuario presione el botón para ver historial.
        BotonHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), HistorialCodigos.class);

                startActivity(intent);

            }
        });

        //Lo que se ejecutará cuando el usuario presione el botón para ingresar al examen.

       BotonIngresarExamen.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            codigo.setError(null);
            String codigoexamen=codigo.getText().toString().trim();
            if("".equals(codigoexamen)){
                codigo.setError("Error.Debe ingresar un código de acceso");
                codigo.requestFocus();
                return;
            }

        DatabaseReference referenciaExamenes = database.getReference().child("Examenes/").child(codigoexamen);


          /*     if(referenciaExamenes==null){
                   Toast.makeText(InicioEstudiante.this,"REFERENCIA NULA.",Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(InicioEstudiante.this,"REFERENCIA NO NULA.",Toast.LENGTH_SHORT).show();
               }*/

               referenciaExamenes.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       Examen examen = snapshot.getValue(Examen.class);

                       if(examen == null){
                           Toast.makeText(InicioEstudiante.this,"El código ingresado no es válido",Toast.LENGTH_SHORT).show();
                       }else{
                         //  Toast.makeText(InicioEstudiante.this,"Sí se encuentra",Toast.LENGTH_SHORT).show();



                           //Nos vamos a la actividad que carga el formulario de preguntas a responder.

                           Intent intent = new Intent(getBaseContext(), CuestionarioExamen.class);

                           intent.putExtra("codigoExamen",examen.getCodigo());
                           intent.putExtra("nombreExamen",examen.getNombre());

                           codigo.setText("");
                           startActivity(intent);




                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                       Log.d("ONCANCELLED_DATABASE_ERROR: ", error.getMessage());
                   }
               });

           }
       });

        Cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(InicioEstudiante.this, Inicio_sesion.class);
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
            Intent intent = new Intent(InicioEstudiante.this, Inicio_sesion.class);
            startActivity(intent);
        }
    }

    private void Inicializar(){
        UsuarioEstudiante =binding.UsuarioEstudiante;
        Cerrarsesion=binding.Botoncerrarsesion;
        BotonIngresarExamen=binding.IngresarExamen;
        BotonHistorial=binding.BotonHistorial;
        codigo=binding.Codigo;


    }
}