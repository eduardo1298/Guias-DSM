package sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.asignaturasAdaptador.ListaAsignaturaAdaptador;
import sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.basedatoshelper.BasedatosHelper;
import sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.modelo.Asignaturas;

public class MainActivity extends AppCompatActivity {
    private ListView lvAsignatura;
    private ListaAsignaturaAdaptador adaptador;
    private List<Asignaturas> AsignaturaLista;
    private BasedatosHelper DBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAsignatura = (ListView)findViewById(R.id.listview_asignatura);
        DBHelper = new BasedatosHelper(this);
        File database =
                getApplicationContext().getDatabasePath(BasedatosHelper.DBNOMBRE);
        if(false == database.exists()) {
            DBHelper.getReadableDatabase();
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copiado de base de datos correcto",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error copiado de base de datos",
                        Toast.LENGTH_SHORT).show();
                return;
            }
        }
        AsignaturaLista = DBHelper.obtenerListaAsignatura();
        adaptador = new ListaAsignaturaAdaptador(this, AsignaturaLista);
        lvAsignatura.setAdapter(adaptador);
    }
    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream =
                    context.getAssets().open(BasedatosHelper.DBNOMBRE);
            String outFileName = BasedatosHelper.DBUBICACION + BasedatosHelper.DBNOMBRE;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","Copia de Base de Datos");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        }
}