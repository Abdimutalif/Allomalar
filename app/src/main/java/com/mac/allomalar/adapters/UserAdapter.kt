package com.mac.allomalar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R
import com.mac.allomalar.models.Alloma

class UserAdapter( val list : List<Alloma>, var onItemUserClick: OnItemUserClick):RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(var view:View): RecyclerView.ViewHolder(view) {
        fun onBind(alloma:Alloma?,position:Int){
            val card = view.findViewById<MaterialCardView>(R.id.card_batafsil)
            card.setOnClickListener {
                onItemUserClick.onClick(alloma, position)
            }
            var tv = view.findViewById<TextView>(R.id.tv_scholar_name)
            tv.text = alloma?.name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_scholar_in_madrasa,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int= list.size

    interface OnItemUserClick{
        fun onClick(alloma: Alloma?, position: Int)
    }
}