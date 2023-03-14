package com.example.mysolelife.contentsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysolelife.R

class ContentRVAdapter(val item: ArrayList<String>) :
    RecyclerView.Adapter<ContentRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: String) {

        }
    }
}