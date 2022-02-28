package com.mac.allomalar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R

class UserAdapter( var onItemUserClick: OnItemUserClick):RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(var view:View): RecyclerView.ViewHolder(view) {
        fun onBind(item:Any?,position:Int){
            val card = view.findViewById<MaterialCardView>(R.id.card_batafsil)
           // card.setOnClickListener {
           //     onItemUserClick.onClick(item, position)
           // }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_madrasa_name,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind("",position)
    }

    override fun getItemCount(): Int=20

    interface OnItemUserClick{
        fun onClick(item: Any?, position: Int)
    }
}