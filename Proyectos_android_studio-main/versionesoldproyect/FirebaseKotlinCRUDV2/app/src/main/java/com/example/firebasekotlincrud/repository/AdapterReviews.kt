package com.example.firebasekotlincrud.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.firebasekotlincrud.R
import com.example.firebasekotlincrud.Reviews
import kotlin.collections.ArrayList


class AdapterReviews(private val reviewslist : ArrayList<Reviews>) : RecyclerView.Adapter<AdapterReviews.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_review,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = reviewslist[position]

        holder.username.text = currentitem.UserName
        holder.userreview.text = currentitem.Review


    }

    override fun getItemCount(): Int {

        return reviewslist.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val username : TextView = itemView.findViewById(R.id.tv_username)
        val userreview : TextView = itemView.findViewById(R.id.tv_review)


    }

}