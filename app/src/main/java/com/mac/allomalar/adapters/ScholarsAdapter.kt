package com.mac.allomalar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R

class ScholarsAdapter(var onItemScholarClick: OnItemScholarClick): RecyclerView.Adapter<ScholarsAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view){
        fun onBind(scholar: Any?, position: Int){
            val card = view.findViewById<MaterialCardView>(R.id.card_batafsil)
            card.setOnClickListener {
                onItemScholarClick.onClick(scholar, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_scholar_in_madrasa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind("", position)
    }

    override fun getItemCount(): Int  = 15

    interface OnItemScholarClick{
        fun onClick(scholar: Any?, position: Int)
    }
}