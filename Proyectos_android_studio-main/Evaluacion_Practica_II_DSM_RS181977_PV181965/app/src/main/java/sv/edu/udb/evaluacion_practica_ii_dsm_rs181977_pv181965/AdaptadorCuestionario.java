package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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

public class AdaptadorCuestionario extends ArrayAdapter<Pregunta> {

    List<Pregunta> preguntas;
    private Activity context;

    public AdaptadorCuestionario(@NonNull Activity context, @NonNull List<Pregunta> preguntas) {
        super(context, R.layout.cuestionario_layout, preguntas);
        this.context = context;
        this.preguntas = preguntas;
    }

    @Override
    public int getCount() {
        return preguntas.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public List<Pregunta> getPreguntas(){
        return preguntas;
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
            rowview = layoutInflater.inflate(R.layout.cuestionario_layout,null);
        else rowview = view;

        TextView Pregunta = rowview.findViewById(R.id.Pregunta);
        RadioButton Opcion1 = rowview.findViewById(R.id.Opcion1);
        RadioButton Opcion2 = rowview.findViewById(R.id.Opcion2);
        RadioButton Opcion3 = rowview.findViewById(R.id.Opcion3);

        Opcion1.setBackgroundResource(R.color.transparente);
        Opcion2.setBackgroundResource(R.color.transparente);
        Opcion3.setBackgroundResource(R.color.transparente);
       // TextView Respuesta = rowview.findViewById(R.id.Respuesta);
     //   TextView OpcionElegida = rowview.findViewById(R.id.Opcionelegida);
           TextView Estado = rowview.findViewById(R.id.Estado);

        if("multiple".equals(preguntas.get(position).getTipo())){
            Pregunta.setText("Pregunta #"+(position+1)+": "+preguntas.get(position).getPregunta());
            Opcion1.setText("Opción 1 : " + preguntas.get(position).getOpcion1());
            Opcion2.setText("Opción 2 : " + preguntas.get(position).getOpcion2());
            Opcion3.setText("Opción 3 : " + preguntas.get(position).getOpcion3());


            if(preguntas.get(position).getRespuestaSeleccionada().equals(preguntas.get(position).getOpcion1())){
                Opcion1.setChecked(true);
            }else if (preguntas.get(position).getRespuestaSeleccionada().equals(preguntas.get(position).getOpcion2())){
                Opcion2.setChecked(true);
            }else if (preguntas.get(position).getRespuestaSeleccionada().equals(preguntas.get(position).getOpcion3())){
                Opcion3.setChecked(true);
            }


            if(preguntas.get(position).getRespuestaCorrecta().equals(preguntas.get(position).getRespuestaSeleccionada())){
                Estado.setText("¡Respuesta Correcta!");
                Estado.setTextColor(context.getResources().getColor(R.color.Green2));
               if(Opcion1.isChecked()){
                   Opcion1.setBackgroundResource(R.drawable.respuestacorrecta);
               }else if (Opcion2.isChecked()){
                   Opcion2.setBackgroundResource(R.drawable.respuestacorrecta);
               }else if (Opcion3.isChecked()){
                   Opcion3.setBackgroundResource(R.drawable.respuestacorrecta);
               }
            }else{
                Estado.setText("¡Respuesta Incorrecta! La respuesta correcta es: " + preguntas.get(position).getRespuestaCorrecta());
                Estado.setTextColor(context.getResources().getColor(R.color.Red2));
                if(Opcion1.isChecked()){
                    Opcion1.setBackgroundResource(R.drawable.respuestaincorrecta);
                }else if (Opcion2.isChecked()){
                    Opcion2.setBackgroundResource(R.drawable.respuestaincorrecta);
                }else if (Opcion3.isChecked()){
                    Opcion3.setBackgroundResource(R.drawable.respuestaincorrecta);
                }
            }


        }else{
            Pregunta.setText("Pregunta #"+(position+1)+": "+preguntas.get(position).getPregunta());
            Opcion1.setText("Opción 1 : " + preguntas.get(position).getOpcion1());
            Opcion2.setText("Opción 2 : " + preguntas.get(position).getOpcion2());

            Opcion3.setVisibility(View.GONE);

            if(preguntas.get(position).getRespuestaSeleccionada().equals(preguntas.get(position).getOpcion1())){
                Opcion1.setChecked(true);
            }else if (preguntas.get(position).getRespuestaSeleccionada().equals(preguntas.get(position).getOpcion2())){
                Opcion2.setChecked(true);
            }



            if(preguntas.get(position).getRespuestaCorrecta().equals(preguntas.get(position).getRespuestaSeleccionada())){
                Estado.setText("¡Respuesta Correcta!");
                Estado.setTextColor(context.getResources().getColor(R.color.Green2));
                if(Opcion1.isChecked()){
                    Opcion1.setBackgroundResource(R.drawable.respuestacorrecta);
                }else if (Opcion2.isChecked()){
                    Opcion2.setBackgroundResource(R.drawable.respuestacorrecta);
                }
            }else{
                Estado.setText("¡Respuesta Incorrecta! La respuesta correcta es: " + preguntas.get(position).getRespuestaCorrecta());
                Estado.setTextColor(context.getResources().getColor(R.color.Red2));
                if(Opcion1.isChecked()){
                    Opcion1.setBackgroundResource(R.drawable.respuestaincorrecta);
                }else if (Opcion2.isChecked()){
                    Opcion2.setBackgroundResource(R.drawable.respuestaincorrecta);
                }
            }

        }
        return rowview;
    }


}
