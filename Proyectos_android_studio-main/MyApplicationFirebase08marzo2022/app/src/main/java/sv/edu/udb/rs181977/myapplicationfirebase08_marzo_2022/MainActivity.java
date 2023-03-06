package sv.edu.udb.rs181977.myapplicationfirebase08_marzo_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth miAuth;
    private FirebaseAuth.AuthStateListener miAuthlistener;
    private Button btnAutenticacion;
    private Button btnCrearCuenta;
    private EditText txtCorreo;
    private EditText txtContraseña;
    private FirebaseUser usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAutenticacion = (Button) findViewById(R.id.btnAutenticarse);
        btnCrearCuenta = (Button) findViewById(R.id.btncrearcuenta);
        txtCorreo = (EditText) findViewById(R.id.txteditcorreo);
        txtContraseña = (EditText) findViewById(R.id.txteditcontrasenia);

        miAuth = FirebaseAuth.getInstance();
        miAuthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (usuario != null) {
                    Toast.makeText(MainActivity.this, "Autenticado onAuthStateChanged", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "No autenticado onAuthStateChanged", Toast.LENGTH_LONG).show();
                }


            }
        };

        btnAutenticacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(txtCorreo.getText().toString()) && !TextUtils.isEmpty(txtContraseña.getText().toString())) {
                    String correo = txtCorreo.getText().toString();
                    String contraseña = txtContraseña.getText().toString();
                    autenticar(correo, contraseña);
                }


            }
        });
    }

    private void autenticar(String correo, String contraseña) {
        miAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Autenticado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "No Autenticado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        miAuth.addAuthStateListener(miAuthlistener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(miAuthlistener!=null){
            miAuth.removeAuthStateListener(miAuthlistener);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.accion_salir) {
            miAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }
}