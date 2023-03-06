package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.ListItem;
import sv.edu.udb.myapplicationrecicycleview01.R;

public class MiAdapter extends RecyclerView.Adapter<MiAdapter.ViewHolder> {
    private Context context;
    private List<ListItem> listaItems;
    private View view;
    public MiAdapter(Context context,List listaItems) {
        this.context = context;
        this.listaItems = listaItems;
    }

    @NonNull
    @Override
    public MiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_fila,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdapter.ViewHolder holder, int position) {
    ListItem item=listaItems.get(position);
    holder.txtasignatura.setText(item.getAsignatura());
    holder.txtcodigo.setText(item.getAsignatura());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtasignatura;
        public TextView txtcodigo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtasignatura = (TextView) itemView.findViewById(R.id.txtxasignatura);
            txtcodigo = (TextView) itemView.findViewById(R.id.txtCodigo);
        }
    }
}

