package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityMainBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityRegistroBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Usuario;

public class Registro extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    private EditText Correo, Contraseña, ConfirmarContraseña, editUsuario;
    private ImageView BotonRegresar;
    private FirebaseAuth miAuth;
    private Button BotonRegistrarse;
    final Handler handler = new Handler();
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        miAuth = FirebaseAuth.getInstance();
        Inicializar();

        BotonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Inicio_sesion.class);
                startActivity(intent);
            }
        });

        BotonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });

    }

    private void RegistrarUsuario() {
        String correo, contraseña, confirmarcontraseña, usuario;
        correo = Correo.getText().toString().trim();
        contraseña = Contraseña.getText().toString().trim();
        confirmarcontraseña = ConfirmarContraseña.getText().toString().trim();
        usuario = editUsuario.getText().toString().trim();
        Correo.setError(null);
        Contraseña.setError(null);
        ConfirmarContraseña.setError(null);
        editUsuario.setError(null);
        final String expresionCorreo = "^[a-zA-Z0-9_.]+[@]{1}[a-z0-9]+[\\.][a-z]{2,3}+$";
        if ("".equals(usuario)){
            editUsuario.setError("Error.Ingresa el campo usuario");
            editUsuario.requestFocus();
            return;
        }else if("".equals(correo)){
            Correo.setError("Error.Ingresa el campo correo");
            Correo.requestFocus();
            return;
        }else if(correo.matches(expresionCorreo)==false){
            Correo.setError("Error.Formato inválido,ejemplo de formato jose@gmail.com");
            Correo.requestFocus();
            return;
        }else if("".equals(contraseña)){
            Contraseña.setError("Error.Ingresa el campo contraseña");
            Contraseña.requestFocus();
            return;
        }else if(contraseña.length()<6){
            Contraseña.setError("La contraseña debe tener por lo menos 6 caracteres");
            Contraseña.requestFocus();
            return;
        }else if("".equals(confirmarcontraseña)){
            ConfirmarContraseña.setError("Error.Ingresa el campo confirmar contraseña");
            ConfirmarContraseña.requestFocus();
            return;
        }else if(!confirmarcontraseña.equals(contraseña)){
            ConfirmarContraseña.setError("Error.Las contraseñas ingresadas no coinciden");
            ConfirmarContraseña.requestFocus();
            return;
        }

        miAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser usuarioactual = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest perfil = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(usuario)
                                    .build();
                            usuarioactual.updateProfile(perfil);

                            Usuario usuario = new Usuario(editUsuario.getText().toString(),correo,"estudiante");
                            DatabaseReference refUsuarios = database.getReference("Usuarios");
                            refUsuarios.child(usuarioactual.getUid()).setValue(usuario);
                            Toast.makeText(getApplicationContext(), "Registro realizado con éxito", Toast.LENGTH_LONG).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Registro.this, Inicio_sesion.class);
                                    startActivity(intent);
                                }
                            }, 500);

                        } else {
                            if("com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account.".equals(task.getException().toString())){
                                Toast.makeText(Registro.this, "Error.El correo que desea registrar ya ha sido ingresado a la aplicación",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error.Fallo al registrarse,por favor intente más tarde", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }


    private void Inicializar(){

        Correo = binding.Correo;
        Contraseña = binding.ContraseA;
        BotonRegresar = binding.BotonRegresar;
        ConfirmarContraseña = binding.ConfirmarContraseA;
        editUsuario = binding.Usuario;
        BotonRegistrarse = binding.BotonRegistrarse;
    }
}