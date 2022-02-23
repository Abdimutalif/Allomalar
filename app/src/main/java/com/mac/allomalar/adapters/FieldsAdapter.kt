package com.mac.allomalar.adapters

import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R

class FieldsAdapter(var list: List<Any>, var onFieldClick: OnFieldClick) : RecyclerView.Adapter<FieldsAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(item: Any?, position: Int ){
           view.setOnClickListener {
               onFieldClick.onClick("", position)
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fields, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(null, position)
    }

    override fun getItemCount(): Int = list.size

    interface OnFieldClick{
        fun onClick(field: Any, position: Int)
    }
}