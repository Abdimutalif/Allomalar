package com.mac.allomalar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R

class MadrasasAdapter(var number: Int, var onClick: MadrasaSetOnClickListener) : RecyclerView.Adapter<MadrasasAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(madrasa: Any, position: Int){
           var card =  view.findViewById<MaterialCardView>(R.id.card_root_name_madrasa)
            card.setOnClickListener {
                onClick.onMadrasaClickListener(madrasa, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_madrasa_name, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind("", position)
    }

    override fun getItemCount(): Int = 5 + number

    interface MadrasaSetOnClickListener{
        fun onMadrasaClickListener(madrasa: Any, position: Int)
    }
}