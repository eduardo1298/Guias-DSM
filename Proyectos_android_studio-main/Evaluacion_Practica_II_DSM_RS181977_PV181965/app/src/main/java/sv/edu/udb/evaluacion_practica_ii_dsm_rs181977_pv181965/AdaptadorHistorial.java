package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.core.view.ViewProcessor;

import java.util.List;

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Examen;
import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class AdaptadorHistorial  extends ArrayAdapter<Examen>{


    List<Examen> codigosExamen;
    private Activity context;

    public AdaptadorHistorial(@NonNull Activity context, @NonNull List<Examen> examenes) {
        super(context, R.layout.historial_layout, examenes);
        this.context = context;
        this.codigosExamen = examenes;
    }

    @Override
    public int getCount() {
        return codigosExamen.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
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
            rowview = layoutInflater.inflate(R.layout.historial_layout,null);
        else rowview = view;

        //


        TextView codigoExamen = rowview.findViewById(R.id.tituloCodigo);
        TextView tituloExamen = rowview.findViewById(R.id.tituloNombre);


        codigoExamen.setText("Código de examen: " + codigosExamen.get(position).getCodigo());
        tituloExamen.setText("Nombre de examen: " + codigosExamen.get(position).getNombre());




        return rowview;
    }

}
