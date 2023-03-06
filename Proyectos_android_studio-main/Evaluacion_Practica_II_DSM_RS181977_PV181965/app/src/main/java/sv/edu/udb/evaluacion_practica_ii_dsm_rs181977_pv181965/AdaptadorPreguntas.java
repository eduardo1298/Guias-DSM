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

import sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos.Pregunta;

public class AdaptadorPreguntas extends ArrayAdapter<Pregunta> {
    List<Pregunta> preguntas;
    private Activity context;

    public AdaptadorPreguntas(@NonNull Activity context, @NonNull List<Pregunta> preguntas) {
        super(context, R.layout.pregunta_layout, preguntas);
        this.context = context;
        this.preguntas = preguntas;
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
            rowview = layoutInflater.inflate(R.layout.pregunta_layout,null);
        else rowview = view;
        TextView Pregunta = rowview.findViewById(R.id.Pregunta);
        TextView Opcion1 = rowview.findViewById(R.id.Opcion1);
        TextView Opcion2 = rowview.findViewById(R.id.Opcion2);
        TextView Opcion3 = rowview.findViewById(R.id.Opcion3);
        TextView Respuesta = rowview.findViewById(R.id.Respuesta);
        if("multiple".equals(preguntas.get(position).getTipo())){
            Pregunta.setText("Pregunta : "+preguntas.get(position).getPregunta());
            Opcion1.setText("Opción 1 : " + preguntas.get(position).getOpcion1());
            Opcion2.setText("Opción 2 : " + preguntas.get(position).getOpcion2());
            Opcion3.setText("Opción 3 : " + preguntas.get(position).getOpcion3());
            Respuesta.setText("Respuesta : " + preguntas.get(position).getRespuestaCorrecta());
        }else{
            Pregunta.setText("Pregunta : "+preguntas.get(position).getPregunta());
            Opcion1.setText("Opción 1 : " + preguntas.get(position).getOpcion1());
            Opcion2.setText("Opción 2 : " + preguntas.get(position).getOpcion2());
            Respuesta.setText("Respuesta : " + preguntas.get(position).getRespuestaCorrecta());
            Opcion3.setHeight(0);
            Opcion3.setWidth(0);
        }
        return rowview;
    }
}
