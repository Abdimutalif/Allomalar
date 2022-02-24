package com.mac.allomalar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mac.allomalar.R
import com.mac.allomalar.models.just_for_test.User

class TestAdapter: RecyclerView.Adapter<TestAdapter.ViewHolder>(){

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun onBind(user: User, position: Int){
            var tv =view.findViewById<TextView>(R.id.name_test)
            tv.text = user.name
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return  oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    fun submitList(list: List<User>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      var view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.onBind(differ.currentList[position], position)
    }

    override fun getItemCount(): Int  = differ.currentList.size
}