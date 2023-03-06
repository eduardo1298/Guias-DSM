package sv.edu.udb.tareainvestigacion2_viewbinding;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import sv.edu.udb.tareainvestigacion2_viewbinding.databinding.EstudianteLayoutBinding;
import sv.edu.udb.tareainvestigacion2_viewbinding.datos.Estudiante;
import sv.edu.udb.tareainvestigacion2_viewbinding.databinding.EstudianteLayoutBinding;

public class AdaptadorEstudiante extends ArrayAdapter<Estudiante> {

    //private  EstudianteLayoutBinding binding;
    private List<Estudiante> estudiantes;
    private final LayoutInflater ObjetoVista;
    
    public AdaptadorEstudiante(List<Estudiante> estudiantes,Context context) {


       super(context, 0, estudiantes);


        this.estudiantes = estudiantes;
        this.ObjetoVista = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return estudiantes.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            EstudianteLayoutBinding binding = EstudianteLayoutBinding.inflate(ObjetoVista, viewGroup, false);
            viewHolder = new ViewHolder(binding);
            view = binding.getRoot();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.binding.tvNombreCompleto.setText("Nombre completo : "+estudiantes.get(posicion).getNombres()+" "+estudiantes.get(posicion).getApellidos());
        viewHolder.binding.tvCarnet.setText("Carné : "+estudiantes.get(posicion).getCarnet());
        viewHolder.binding.tvCarrera.setText("Carrera: " + estudiantes.get(posicion).getProgramaEstudio());
        viewHolder.binding.tvCorreo.setText("Correo: " + estudiantes.get(posicion).getCorreo());
        viewHolder.binding.tvTelefono.setText("Teléfono: " + estudiantes.get(posicion).getTelefono());
        return view;
    }

    static class ViewHolder {

        private final EstudianteLayoutBinding binding;

        public ViewHolder(EstudianteLayoutBinding binding) {
            this.binding = binding;
        }
    }
}
