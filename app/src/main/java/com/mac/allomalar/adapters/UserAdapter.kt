package com.mac.allomalar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mac.allomalar.R
import com.mac.allomalar.db.database.AppDatabase
import com.mac.allomalar.models.Alloma
import com.mac.allomalar.models.Image
import com.mac.allomalar.repository.Repository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserAdapter(var context: Context, val list: List<Alloma>, var onItemUserClick: OnItemUserClick) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var db: AppDatabase = AppDatabase.getInstance(context)

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(alloma: Alloma?, position: Int) {
            val card = view.findViewById<MaterialCardView>(R.id.card_batafsil)
            val imageView = view.findViewById<ImageView>(R.id.iv_image_of_scholar)
            card.setOnClickListener {
                onItemUserClick.onClick(alloma, position)
            }
            var tv = view.findViewById<TextView>(R.id.tv_scholar_name)
            tv.text = alloma?.name
            CoroutineScope(Dispatchers.Main).launch {
                imageView.setImageBitmap(db.imageDao().getImageById(alloma?.image_url!!)?.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scholar_in_madrasa, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemUserClick {
        fun onClick(alloma: Alloma?, position: Int)
    }
}