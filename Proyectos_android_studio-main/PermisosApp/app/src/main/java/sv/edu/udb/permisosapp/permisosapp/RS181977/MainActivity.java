package sv.edu.udb.permisosapp.permisosapp.RS181977;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity {

    private TextView lblPermiso;
    private TextView lblContacto;
    private final int MY_PERMISSONS=100; //Código que utilizamos para solicitar los permisos, puede ser cualquier número

    private final int OPEN_CONTACT=200; //Código que utilizamos para abrir la lista de contactos,puede ser cualquier número

    private final String str_permitido="PERMITIDO";
    private final String str_denegado="DENEGADO";
    private String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estado="Estado del permiso:";

        lblPermiso=(TextView)findViewById(R.id.lblPermiso);
        lblContacto=(TextView)findViewById(R.id.lblContacto);

        if(verificarPermiso())
            lblPermiso.setText(estado+ " "+str_permitido);
        else
            lblPermiso.setText(estado+ " "+str_denegado);
    }

    public boolean verificarPermiso(){
        //Comprobando la versión del dispositivo, en este caso que sea la versión Android 6.0
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M) return true;

        //Comprobando que los permisos ya hayan sido aceptados por el usuario
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED) return true;

        //Los permisos fueron aceptados por el usuario
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void on_Click(View view){
        if(verificarPermiso()) {
            lblPermiso.setText(estado + " " + str_permitido);
            //Creamos un Intent para abrir los contactos
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            //Lanzamos el Intent y se espera a que se seleccione un contacto
            startActivityForResult(intent, OPEN_CONTACT);
        }else{
            //Solicitamos los permisos para abrir los contactos
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSONS);
        }
    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OPEN_CONTACT:
                    Uri contactUri = data.getData();
                    // Con el URI consultamos La informacion del contacto seleccionado
                    Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
                    String nombre = "\n" + "Contacto Seleccionado: " + "\n";
                    // Verificacion si el contacto existe
                    if (cursor.moveToFirst()) {
                        // Obtenemos el nombre del contacto
                        nombre = nombre + cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    }
                    lblContacto.setText(nombre);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                lblPermiso.setText(estado + " " + str_permitido);
                // Creamos el Intent para abrir los contactos
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                //Abrimos los contactos
                startActivityForResult(intent, OPEN_CONTACT);
            } else {
                lblPermiso.setText(estado + " " + str_denegado);
            }
        }
    }
}