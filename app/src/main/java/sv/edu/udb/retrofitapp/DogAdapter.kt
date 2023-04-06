package sv.edu.udb.retrofitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclearview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView

class DogAdapter(private val images: List<String>?): RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder{
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int){
        holder.bind(images!![position])
    }
}