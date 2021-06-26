package com.example.baicuoiky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class AdapterReading(private val exampleList: List<MyModel>) :
    RecyclerView.Adapter<AdapterReading.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
            parent, false)
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.textView1.text = currentItem.terms
        holder.textView2.text = currentItem.defines
    }
    override fun getItemCount() = exampleList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.textView_terms1
        val textView2: TextView = itemView.textView_defines1
    }
}