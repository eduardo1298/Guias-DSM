package sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.asignaturasAdaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.R;
import sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.modelo.Asignaturas;

public class ListaAsignaturaAdaptador extends BaseAdapter {
    private Context contexto;
    private List<Asignaturas> asignaturaLista;

    public ListaAsignaturaAdaptador(Context contexto, List<Asignaturas> miasignaturaLista) {
        this.contexto = contexto;
        this.asignaturaLista = miasignaturaLista;
    }

    @Override
    public int getCount() {
        return asignaturaLista.size();
    }

    @Override
    public Object getItem(int posicion) {
        return asignaturaLista.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return asignaturaLista.get(posicion).getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup padre) {
       View vista = View.inflate(contexto, R.layout.asignatura_lista,null);
        TextView tvCodigo=(TextView) vista.findViewById(R.id.tv_codigo);
        TextView tvNombre=(TextView) vista.findViewById(R.id.tv_nombre);
        TextView tvUv=(TextView) vista.findViewById(R.id.tv_uv);
        tvCodigo.setText(asignaturaLista.get(posicion).getCodigo());
        tvUv.setText(String.valueOf(asignaturaLista.get(posicion).getUv()));
        tvNombre.setText(asignaturaLista.get(posicion).getNombre());
        return vista;

    }
}
