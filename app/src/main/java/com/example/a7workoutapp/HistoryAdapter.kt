package com.example.a7workoutapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.template_item_history.view.*

class HistoryAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.Viewholder>() {

    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
        val llHistoryMainItem = view.ll_history_item
        val tvItem = view.tvItem
        val tvPosition = view.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(context).inflate(R.layout.template_item_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val date: String = items.get(position)

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        if (position % 2 == 0) {
            holder.llHistoryMainItem.setBackgroundColor(
                ContextCompat.getColor(context, R.color.Accent)
            )
        } else {
            holder.llHistoryMainItem.setBackgroundColor(
                ContextCompat.getColor(context, R.color.Accentlight)
            )
        }
    }


}