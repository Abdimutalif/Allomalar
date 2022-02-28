package com.mac.allomalar.adapters

import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R
import com.mac.allomalar.models.Subject
import com.mac.allomalar.utils.Constants
import com.squareup.picasso.Picasso

class FieldsAdapter(var list: List<Subject>, var onFieldClick: OnFieldClick) :
    RecyclerView.Adapter<FieldsAdapter.ViewHolder>() {

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(subject: Subject?, position: Int) {
            view.setOnClickListener {
                onFieldClick.onClick(subject, position)
            }
            val tv = view.findViewById<TextView>(R.id.tv_field_name_2)
            val imageView = view.findViewById<ImageView>(R.id.iv_field_logo)
            tv.text = subject?.name

//            Picasso.get()
//                .load(Constants.BASE_URL + subject?.image_url)
//                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fields, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnFieldClick {
        fun onClick(subject: Subject?, position: Int)
    }
}