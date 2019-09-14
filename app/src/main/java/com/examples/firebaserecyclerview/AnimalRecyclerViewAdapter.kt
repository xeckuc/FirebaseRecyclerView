package com.examples.firebaserecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.animal_item.view.*

class AnimalRecyclerViewAdapter(private val context: Context, private var list: ArrayList<Animal>):
    RecyclerView.Adapter<AnimalRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.animal_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.name.text = list[position].name
        Glide.with(context).load(list[position].imgUrl).into(holder?.image)

    }

    override fun getItemCount(): Int = list.size

    public class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val name = view.tvName
        val image = view.ivImage
    }
}