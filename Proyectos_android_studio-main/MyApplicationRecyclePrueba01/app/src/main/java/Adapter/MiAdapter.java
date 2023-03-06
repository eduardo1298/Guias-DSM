package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplicationrecycleprueba01.R;
import java.util.List;
import Model.ListaItem;

public class MiAdapter extends RecyclerView.Adapter<MiAdapter.ViewHolder> {
    private Context context;
    private List<ListaItem> listaItems;

    public MiAdapter(Context context, List listaItems ){
        this.context = context;
        this.listaItems = listaItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_filas,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdapter.ViewHolder holder, int position) {
        ListaItem item = listaItems.get(position);
        holder.txtAsignatura.setText(item.getAsignatura());
        holder.txtDescripcion.setText(item.getCodigo());
        holder.txtUv.setText(" UV: "+String.valueOf(item.getUv()));
        holder.txtPrerequisito.setText(" Prerequisitos: "+item.getPrerequisito());

    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtAsignatura;
        public TextView txtDescripcion;
        public TextView txtUv;
        public TextView txtPrerequisito;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAsignatura = (TextView) itemView.findViewById(R.id.txtAsignatura);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtCodigo);
            txtUv = (TextView) itemView.findViewById(R.id.txtUv);
            txtPrerequisito = (TextView) itemView.findViewById(R.id.txtPrerequisito);
        }
    }
}
