package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;

public class AdaptadorExamenes extends ArrayAdapter<Examen> {
    List<Examen> examenes;
    private Activity context;

    public AdaptadorExamenes(@NonNull Activity context, @NonNull List<Examen> examenes) {
        super(context, R.layout.examen_layout, examenes);
        this.context = context;
        this.examenes = examenes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        if (view == null)
            rowview = layoutInflater.inflate(R.layout.examen_layout,null);
        else rowview = view;

        TextView NombreExamen = rowview.findViewById(R.id.NombreExamenLista);
        TextView CodigoExamen = rowview.findViewById(R.id.CodigoExamenLista);
        NombreExamen.setText("Nombre del examen : "+examenes.get(position).getNombre());
        CodigoExamen.setText("Código de acceso : " + examenes.get(position).getCodigo());


        return rowview;
    }
}
