package com.example.a7workoutapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_excercise_status.view.*

class ExcerciseStatusAdapter(val items: ArrayList<ExcerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExcerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.tv_item;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_excercise_status, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExcerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
        if(model.getIsSelected()){
            holder.tvItem.background=ContextCompat.getDrawable(context,R.drawable.item_circular_thin_color_accent_border)
            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        }else if(model.getIsCompleted()){
            holder.tvItem.background=ContextCompat.getDrawable(context,R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        }else{
            holder.tvItem.background=ContextCompat.getDrawable(context,R.drawable.item_circular_color_gray_background)
            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}