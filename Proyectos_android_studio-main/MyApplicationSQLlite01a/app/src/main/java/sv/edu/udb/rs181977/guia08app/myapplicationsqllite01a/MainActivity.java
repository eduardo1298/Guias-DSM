package sv.edu.udb.rs181977.guia08app.myapplicationsqllite01a;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String texto = "";
    SQLiteDatabase db = null;
    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textViewDB);
        db = this.openOrCreateDatabase("AsignaturasDB", MODE_PRIVATE, null);
        db.execSQL("create table if not exists "+
                " asignatura (id integer primary key, codigo text,"+
                " nombre text, uv integer);");
        ejecutarSQL();
        mostrarTabla();
        db.close();
    }

    void mostrarTabla(){
        StringBuilder stringBuilder01 = new StringBuilder();
        int numeroFilas=cursor.getCount();
        stringBuilder01.append("id\tCodigo\tNombre\tUV\n");
        cursor.moveToFirst();
        for (int i=1;i<=numeroFilas;i++){
            int id=cursor.getInt(0);
            String codigo=cursor.getString(1);
            String nombre=cursor.getString(2);
            int uv=cursor.getInt(3);
            texto=id+" "+codigo+" "+nombre+" "+uv +"\n";
            stringBuilder01.append(texto);
            cursor.moveToNext();
        }
        //Toast.makeText(MainActivity.this,texto,Toast.LENGTH_LONG).show();
        tv.setText(stringBuilder01);
    }

    void ejecutarSQL(){
        cursor= db.rawQuery("select * from asignatura",null);
        int numeroFilas=cursor.getCount();
        for (int i=1; i<=numeroFilas; i++)
            db.execSQL("delete from asignatura where id="+i+";");
        db.execSQL("insert into asignatura (codigo,nombre,uv) "+
                " values('SIO141','Sistemas Operativos',4);");
        db.execSQL("insert into asignatura (codigo,nombre,uv) "+
                " values('ARL141','Implementacion Redes Linux',3);");
        db.execSQL("insert into asignatura (codigo,nombre,uv) "+
                " values('IRW141','Implementacion Redes Windows',3);");
        db.execSQL("insert into asignatura (codigo,nombre,uv) "+
                " values('INW141','Ingenieria de la Web',3);");
        db.execSQL("insert into asignatura (codigo,nombre,uv) "+
                " values('JAA141','Java Avanzado',3);");
    }


}