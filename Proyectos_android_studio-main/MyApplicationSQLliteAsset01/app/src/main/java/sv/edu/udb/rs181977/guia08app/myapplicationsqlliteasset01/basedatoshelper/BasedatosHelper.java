package sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.basedatoshelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.modelo.Asignaturas;

public class BasedatosHelper extends SQLiteOpenHelper {
    public static final String DBNOMBRE="muestra01.db";
    public static final String DBUBICACION="/data/data/sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01/databases/";
    private Context contexto;
    private SQLiteDatabase AsignaturaBasedatos;
    public BasedatosHelper(@Nullable Context contexto) {
        super(contexto,DBNOMBRE,null,1);
        this.contexto=contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbRuta=contexto.getDatabasePath(DBNOMBRE).getPath();
        if(AsignaturaBasedatos!=null && AsignaturaBasedatos.isOpen()){
            return;
        }
        AsignaturaBasedatos=SQLiteDatabase.openDatabase(dbRuta,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(AsignaturaBasedatos!=null ){
            AsignaturaBasedatos.close();
        }
    }

    public List<Asignaturas> obtenerListaAsignatura(){
        Asignaturas asignatura = null;
        List<Asignaturas> asignaturaLista = new ArrayList<>();
        openDatabase();
        Cursor cursor = AsignaturaBasedatos.rawQuery("select * from Asignatura", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            asignatura = new Asignaturas(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2),cursor.getInt(3));
            asignaturaLista.add(asignatura);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return asignaturaLista;
    }
}
