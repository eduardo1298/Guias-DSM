package sv.edu.udb.rs181977.myapplicationfirebase08_marzo_2022.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import sv.edu.udb.rs181977.myapplicationfirebase08_marzo_2022.Modelo.Blog;
import sv.edu.udb.rs181977.myapplicationfirebase08_marzo_2022.R;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {

    private Context contexto;
    private List<Blog> blogLista;

    public BlogRecyclerAdapter(Context contexto, List<Blog> blogList) {
        this.contexto = contexto;
        this.blogLista = blogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_fila_post, parent, false);

        return new ViewHolder(view, contexto);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Blog blog = blogLista.get(position);
        String imagenUrl = null;

        holder.txtviewTitulo.setText(blog.getTitulo());
        holder.txtViewDescescripcion.setText(blog.getDescripcion());

        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(Long.valueOf(blog.getFechahora())).getTime());

        holder.txtViewFechaHora.setText(formattedDate);

        imagenUrl = blog.getImagen();


        /*
        Picasso.with(contexto)
                .load(imagenUrl)
                .into(holder.imageviewImagen);

        */
    }

    @Override
    public int getItemCount() {
        return blogLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtviewTitulo;
        public TextView txtViewDescescripcion;
        public TextView txtViewFechaHora;
        public ImageView imageviewImagen;
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            contexto = ctx;

            txtviewTitulo = (TextView) view.findViewById(R.id.txtViewListaPostTitulo);

            imageviewImagen = (ImageView) view.findViewById(R.id.PostListaImagen);
            txtViewFechaHora = (TextView) view.findViewById(R.id.txtViewfechatitulo);

            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Para ir a la siguiente Activity
                }
            });

        }
    }
}
