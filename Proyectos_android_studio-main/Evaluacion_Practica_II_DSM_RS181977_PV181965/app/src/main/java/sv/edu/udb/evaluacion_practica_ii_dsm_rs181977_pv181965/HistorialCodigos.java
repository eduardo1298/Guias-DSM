package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityCuestionarioExamenBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.databinding.ActivityHistorialCodigosBinding;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class HistorialCodigos extends AppCompatActivity {


    private ActivityHistorialCodigosBinding binding;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    List<Examen> examenInfo; //Usamos la clase "Examen" para trabajar con los "códigos" ya que la clase examen contiene
                          //el atributo "código" y el "nombre", atributos que nos interesan.
    ListView ListaHistorial;
    private FirebaseAuth miAuth;
    private Button botonVolver;

     int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistorialCodigosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        miAuth = FirebaseAuth.getInstance();
        contador=0;
        Inicializar();
        botonVolver = binding.BotonVolver;

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), InicioEstudiante.class);

                startActivity(intent);
                finish();
            }
        });

        String usuarioActual = miAuth.getCurrentUser().getUid();

       // DatabaseReference referenciaHistorial = database.getReference("CodigoExamen").child(usuarioActual).child("Codigos");





        //
        ListaHistorial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent intent = new Intent(getBaseContext(), CuestionarioExamen.class);

                intent.putExtra("codigoExamen",examenInfo.get(posicion).getCodigo());
                intent.putExtra("nombreExamen",examenInfo.get(posicion).getNombre());
                startActivity(intent);
            }
        });

        //

    }


    public void Inicializar(){
        ListaHistorial = binding.ListaHistorial;
        examenInfo = new ArrayList<>();

        String usuarioActual = miAuth.getCurrentUser().getUid();

        DatabaseReference referenciaHistorial = database.getReference("CodigoExamen").child(usuarioActual).child("Codigos");
        Query consultaOrdenada = referenciaHistorial.orderByChild("Codigos");



        //Código que hace que se muestre los códigos (exámenes) a los que tiene acceso el usuario (estudiante).
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                examenInfo.removeAll(examenInfo);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Examen codigo = dato.getValue(Examen.class);
                    codigo.setKey(dato.getKey());
                    examenInfo.add(codigo);
                    contador++;
                }

                AdaptadorHistorial adapter = new AdaptadorHistorial(HistorialCodigos.this, examenInfo );

                ListaHistorial.setAdapter(adapter);
         /*  if(examenInfo.size()==0){
               Toast.makeText(HistorialCodigos.this,"Aún no has accedido a un exámen ",Toast.LENGTH_SHORT).show();
                }else{
               Toast.makeText(HistorialCodigos.this,"Has accedido a : " + String.valueOf(examenInfo.size())+" Examenes",Toast.LENGTH_SHORT).show();
                }
        */

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


}