package com.mac.allomalar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R
import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.Madrasa
import com.mac.allomalar.models.MadrasaAndAllomas

class ScholarsAdapter(var list: List<MadrasaAndAllomas>, var onItemScholarClick: OnItemScholarClick): RecyclerView.Adapter<ScholarsAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View): RecyclerView.ViewHolder(view){
        fun onBind(madrasaAndAllomas: MadrasaAndAllomas?, position: Int){
            val card = view.findViewById<MaterialCardView>(R.id.card_batafsil)
            card.setOnClickListener {
                onItemScholarClick.onClick(madrasaAndAllomas, position)
            }

            val tv =  view.findViewById<TextView>(R.id.tv_scholar_name)
            tv.text = madrasaAndAllomas?.let {
                it.name
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_scholar_in_madrasa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int  = list.size

    interface OnItemScholarClick{
        fun onClick(madrasaAndAllomas: MadrasaAndAllomas?, position: Int)
    }
}