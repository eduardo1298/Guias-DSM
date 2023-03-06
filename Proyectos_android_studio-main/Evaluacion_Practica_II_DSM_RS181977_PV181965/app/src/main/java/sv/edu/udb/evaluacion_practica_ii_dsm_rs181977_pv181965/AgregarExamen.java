package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ThreadLocalRandom;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityAgregarExamenBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityInicioSesionBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;

public class AgregarExamen extends AppCompatActivity {
    private ActivityAgregarExamenBinding binding;
    private Button BotonAgregarExamen;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference referenciaExamenes = database.getReference("Examenes");
    private EditText NombreExamen,CodigoExamen;
    private ImageView BotonRegresar;
    String key="",nombre="",codigo="",accion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarExamenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Inicializar();


        BotonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarExamen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
//Función para recorrer de forma aleatoria el banco de caracteres
    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
//Función para generar código
    public static String GenerarCodigo(int longitud) {
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // Cadena donde se va a generar nuestro código aleatorio de 5 caracteres
        String cadena = "";
        for (int contador = 0; contador < longitud; contador++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    void Inicializar(){
        BotonAgregarExamen=binding.BotonCrearExamen;
        NombreExamen=binding.ExamenNombre;
        CodigoExamen=binding.CodigoExamen;
        BotonRegresar=binding.botonRegresar;

        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        codigo = datos.getString("codigo");
        nombre=datos.getString("nombre");
        accion=datos.getString("accion");
        NombreExamen.setText(nombre);
        CodigoExamen.setText(codigo);
        if("".equals(CodigoExamen.getText().toString())){
            CodigoExamen.setText(GenerarCodigo(5));
        }
        BotonAgregarExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = NombreExamen.getText().toString().trim();
                String codigo = CodigoExamen.getText().toString().trim();
                NombreExamen.setError(null);
                if ("".equals(nombre)){
                    NombreExamen.setError("Error.Ingresa el campo nombre del examen");
                    NombreExamen.requestFocus();
                    return;
                }

                // Se forma objeto persona
                Examen examen = new Examen(nombre,codigo);

                if (accion.equals("agregar")) { //Agregar usando push()
                    MainActivity.referenciaExamen.child(codigo).setValue(examen);
                }
                else // Editar usando setValue
                {
                    MainActivity.referenciaExamen.child(codigo).setValue(examen);
                }
                finish();
            }
        });
    }
}