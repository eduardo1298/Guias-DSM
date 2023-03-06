package sv.edu.udb.guia12app.content.provider.RS181977;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.NonReadableChannelException;

public class add_student extends AppCompatActivity {
private TextView Nombre,Apellido,Carnet;
private Button BotonAgregar;
    SQLiteDatabase db = null;
    Cursor cursor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        db = this.openOrCreateDatabase("students.db", MODE_PRIVATE, null);
        inicializar();

        BotonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nombre.setError(null);
                Apellido.setError(null);
                Carnet.setError(null);
                String nombre=Nombre.getText().toString().trim();
                String apellido=Apellido.getText().toString().trim();
                String carnet=Carnet.getText().toString().trim();
                final String expresionCarnet = "^[a-zA-Z]{2}+[0-9]{6}$";
                if("".equals(nombre)){
                    Nombre.setError("Error.Ingresa el campo nombres");
                    Nombre.requestFocus();
                    return;
                }else if("".equals(apellido)){
                    Apellido.setError("Error.Ingresa el apellidos");
                    Apellido.requestFocus();
                    return;
                }else if("".equals(carnet)){
                    Carnet.setError("Error.Ingresa el campo carnet");
                    Carnet.requestFocus();
                    return;
                }else if(carnet.matches(expresionCarnet)==false){
                    Carnet.setError("Error.Formato inválido,ejemplo de formato YY181920");
                    Carnet.requestFocus();
                    return;
                }


                AgregarRegistro(db,nombre,apellido,carnet.toUpperCase());
                Toast.makeText(add_student.this, "Estudiante agregado con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(add_student.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    void inicializar(){
        Nombre=findViewById(R.id.Nombre);
        Apellido=findViewById(R.id.Apellido);
        Carnet=findViewById(R.id.Carnet);
        BotonAgregar=findViewById(R.id.BotonAgregarEstudiante);
    }

    private void AgregarRegistro(SQLiteDatabase database,String nombre,String apellido,String carnet) {

        ContentValues values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, nombre);
        values.put(StudentsContract.Columnas.APELLIDO, apellido);
        values.put(StudentsContract.Columnas.CARNET,carnet);
        database.insert(StudentsContract.STUDENTS, null, values);
    }
}