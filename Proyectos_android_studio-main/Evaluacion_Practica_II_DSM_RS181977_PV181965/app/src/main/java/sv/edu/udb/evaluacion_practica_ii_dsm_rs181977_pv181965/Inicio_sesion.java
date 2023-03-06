package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityInicioSesionBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityRegistroBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Usuario;

public class Inicio_sesion extends AppCompatActivity {
    private ActivityInicioSesionBinding binding;
    private EditText Correo, Contraseña;
    private Button BotonIniciarSesion;
    private TextView TvOlvidarContraseña, TvRegistrarse;
    private FirebaseAuth miAuth;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioSesionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        inicializar();
        miAuth = FirebaseAuth.getInstance();

        BotonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion();
            }
        });

        TvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio_sesion.this, Registro.class);
                startActivity(intent);
            }
        });

        TvOlvidarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio_sesion.this, RecuperarContrasena.class);
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
        if (usuario != null) {
         // FirebaseAuth.getInstance().signOut();
            FirebaseUser usuarioactual = FirebaseAuth.getInstance().getCurrentUser();
            if(usuarioactual.getUid()!=null){
                DatabaseReference referenciausuarios = database.getReference().child("Usuarios/").child(usuarioactual.getUid());

                referenciausuarios.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        String rol = usuario.getRol();
                        cargarinicio(rol);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                        Log.w("TAG", "Fallo en la lectura de datos.", error.toException());
                    }
                });
            }

        }

    }

    private void IniciarSesion() {

        String correo, contraseña;
        correo = Correo.getText().toString().trim();
        contraseña = Contraseña.getText().toString().trim();
        Correo.setError(null);
        Contraseña.setError(null);
        final String expresionCorreo = "^[a-zA-Z0-9_.]+[@]{1}[a-z0-9]+[\\.][a-z]{2,3}+$";
        if ("".equals(correo)) {
            Correo.setError("Error.Ingresa el campo correo");
            Correo.requestFocus();
            return;
        } else if(correo.matches(expresionCorreo)==false) {
                Correo.setError("Error.Formato inválido,ejemplo de formato jose@gmail.com");
                Correo.requestFocus();
                return;
        } else if ("".equals(contraseña)) {
            Contraseña.setError("Error.Ingresa el campo contraseña");
            Contraseña.requestFocus();
            return;
        }

        miAuth.signInWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser usuarioactual = FirebaseAuth.getInstance().getCurrentUser();

                            DatabaseReference referenciausuarios = database.getReference().child("Usuarios/").child(usuarioactual.getUid());

                            referenciausuarios.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                                    String rol = usuario.getRol();
                                    cargarinicio(rol);
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {

                                    Log.w("TAG", "Fallo en la lectura de datos.", error.toException());
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Fallo al iniciar sesión, por favor verifique que los datos ingresados pertenezcan a una cuenta válida", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


    private void cargarinicio(String rol) {
        if (rol.equals("estudiante")) {
            Intent intent = new Intent(Inicio_sesion.this, InicioEstudiante.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Sesión iniciada con éxito", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(Inicio_sesion.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Sesión iniciada con éxito", Toast.LENGTH_LONG).show();
        }

    }

    private void inicializar() {
        Correo = binding.Correo;
        Contraseña = binding.ContraseA;
        BotonIniciarSesion = binding.BotonIniciarsesion;
        TvRegistrarse = binding.EnlaceRegistro;
        TvOlvidarContraseña = binding.RecuperarcontraseA;

    }
}