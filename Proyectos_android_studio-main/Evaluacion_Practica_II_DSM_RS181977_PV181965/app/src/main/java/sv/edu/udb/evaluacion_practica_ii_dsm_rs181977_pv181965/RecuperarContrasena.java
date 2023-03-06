package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityInicioSesionBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityRecuperarContrasenaBinding;

public class RecuperarContrasena extends AppCompatActivity {
    private ActivityRecuperarContrasenaBinding binding;
    private FirebaseAuth miAuth;
    private Button BotonEnviar;
    private EditText Correo;
    private ImageView BotonRegreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarContrasenaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        inicializar();
        miAuth = FirebaseAuth.getInstance();

        BotonRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecuperarContrasena.this, Inicio_sesion.class);
                startActivity(intent);
            }
        });

        BotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo=Correo.getText().toString().trim();
                final String expresionCorreo = "^[a-zA-Z0-9_.]+[@]{1}[a-z0-9]+[\\.][a-z]{2,3}+$";
                Correo.setError(null);
                if("".equals(correo)){
                    Correo.setError("Error.Debe ingresar el campo correo");
                    Correo.requestFocus();
                    return;
                }else if(correo.matches(expresionCorreo)==false) {
                    Correo.setError("Error.Formato inválido,ejemplo de formato jose@gmail.com");
                    Correo.requestFocus();
                    return;
                }

                miAuth.sendPasswordResetEmail(correo)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RecuperarContrasena.this, "Correo enviado,siga las instrucciones", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(RecuperarContrasena.this, "Error.El correo no ha sido enviado, por favor intente más tarde", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
    void inicializar(){
        Correo=binding.Correo;
        BotonEnviar=binding.BotonEnviarCorreo;
        BotonRegreso=binding.botonRegresar;
    }
}