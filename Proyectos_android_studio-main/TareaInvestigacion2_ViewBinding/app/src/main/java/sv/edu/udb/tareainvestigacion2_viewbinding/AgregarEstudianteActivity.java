package sv.edu.udb.tareainvestigacion2_viewbinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sv.edu.udb.tareainvestigacion2_viewbinding.databinding.ActivityAgregarEstudianteBinding;
import sv.edu.udb.tareainvestigacion2_viewbinding.datos.Estudiante;

public class AgregarEstudianteActivity extends AppCompatActivity {
    private ActivityAgregarEstudianteBinding binding;
    EditText edtApellidos, edtNombres,edtCarnet,edtCorreo,edtTelefono;
    String key="",nombres="",apellidos="",accion="",carnet="",correo="",telefono="",plan_estudio="";
    Spinner SpEstudio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarEstudianteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        inicializar();
    }
    private void inicializar() {
        edtNombres = binding.edtNombres;
        edtApellidos = binding.edtApellidos;
        edtCarnet = binding.edtCarnet;
        edtCorreo = binding.edtcorreo;
        edtTelefono = binding.edtelefono;
        SpEstudio = binding.SpinnerPlanes;


        // Obtención de datos que envia actividad anterior
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        nombres = datos.getString("nombres");
        apellidos=datos.getString("apellidos");
        carnet=datos.getString("carnet");
        plan_estudio=datos.getString("programaestudio");
        correo=datos.getString("correo");
        telefono=datos.getString("telefono");
        accion=datos.getString("accion");
        edtNombres.setText(nombres);
        edtApellidos.setText(apellidos);
        edtCarnet.setText(carnet);
        edtCorreo.setText(correo);
        edtTelefono.setText(telefono);
        if(plan_estudio.equals("Técnico en Ingeniería en Computación")){
            SpEstudio.setSelection(1);
        }else if(plan_estudio.equals("Técnico en Desarrollo de Aplicaciones Móviles")){
            SpEstudio.setSelection(2);
        }else if(plan_estudio.equals("Ingeniería en Ciencias de la Computación")){
            SpEstudio.setSelection(3);
        }else if(plan_estudio.equals("Maestría en Arquitectura de Software")){
            SpEstudio.setSelection(4);
        }else if(plan_estudio.equals("Maestría en Seguridad y Gestión de Riesgos Informáticos")){
            SpEstudio.setSelection(5);
        }

    }

    public void guardar(View v){
        String nombres = edtNombres.getText().toString();
        String apellidos = edtApellidos.getText().toString();
        String carnet = edtCarnet.getText().toString();
        String correo = edtCorreo.getText().toString();
        String telefono = edtTelefono.getText().toString();
        String Plan_estudio = SpEstudio.getSelectedItem().toString();
        edtNombres.setError(null);
        edtApellidos.setError(null);
        edtCarnet.setError(null);
        edtCorreo.setError(null);
        edtTelefono.setError(null);

        final String expresionCarnet = "^[a-zA-Z]{2}+[0-9]{6}$";
        final String expresionCorreo = "^[a-zA-Z0-9_.]+[@]{1}[a-z0-9]+[\\.][a-z]{2,3}+$";
        final String expresionTelefono = "^[0-9]{8}$";
        if ("".equals(edtNombres.getText().toString().trim())) {
            edtNombres.setError("Error.Ingresa el campo nombres");
            edtNombres.requestFocus();
            return;
        }else if("".equals(edtApellidos.getText().toString().trim())){
            edtApellidos.setError("Error.Ingresa el apellidos");
            edtApellidos.requestFocus();
            return;
        }else if("".equals(edtCarnet.getText().toString().trim())){
            edtCarnet.setError("Error.Ingresa el campo carnet");
            edtCarnet.requestFocus();
            return;
        }else if(edtCarnet.getText().toString().matches(expresionCarnet)==false){
            edtCarnet.setError("Error.Formato inválido,ejemplo de formato YY181920");
            edtCarnet.requestFocus();
            return;
        }else if(SpEstudio.getSelectedItemPosition()==0){
            Toast.makeText(getApplicationContext(),"Error, Seleccione un plan de estudio",Toast.LENGTH_SHORT).show();
            SpEstudio.requestFocus();
            return;
        }else if("".equals(edtCorreo.getText().toString().trim())){
            edtCorreo.setError("Error.Ingresa el campo correo");
            edtCorreo.requestFocus();
            return;
        }else if(edtCorreo.getText().toString().matches(expresionCorreo)==false){
            edtCorreo.setError("Error.Formato inválido,ejemplo de formato jose@gmail.com");
            edtCorreo.requestFocus();
            return;
        }else if("".equals(edtTelefono.getText().toString().trim())){
            edtTelefono.setError("Error.Ingresa el campo teléfono");
            edtTelefono.requestFocus();
            return;
        } else if(edtTelefono.getText().toString().matches(expresionTelefono)==false) {
            edtTelefono.setError("Error.Formato inválido,ejemplo de formato 78009321");
            edtTelefono.requestFocus();
            return;
        }

        // Se forma objeto estudiante
        Estudiante estudiante = new Estudiante(nombres,apellidos,carnet.toUpperCase(),Plan_estudio,correo,telefono);

        if (accion.equals("agregar")) { //Agregar usando push()
            EstudianteActivity.refEstudiante.push().setValue(estudiante);
        }
        else // Editar usando setValue
        {
            EstudianteActivity.refEstudiante.child(key).setValue(estudiante);
        }
        finish();
    }
    public void cancelar(View v){
        finish();
    }
}