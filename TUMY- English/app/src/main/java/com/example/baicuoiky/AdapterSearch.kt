package com.example.baicuoiky

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.search_item.view.*

class AdapterSearch(private var exampleList: List<modelMain>) :
    RecyclerView.Adapter<AdapterSearch.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_item,
            parent, false)
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.textView1.text = currentItem.title
        holder.textView2.text = currentItem.count.toString()
        holder.textView3.text = currentItem.name
    }
    override fun getItemCount() = exampleList.size
    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.textView_title
        val textView2: TextView = itemView.textView_count
        val textView3: TextView = itemView.textView_UserID
        init{
            itemView.setOnClickListener {
                val position:Int = adapterPosition
                val currentItem = exampleList[position]
                val i = Intent(itemView.context,term::class.java)
                i.putExtra("data" , currentItem.title)
                i.putExtra("IDdata" , currentItem.name)
                itemView.context.startActivity(i)
            }
        }
    }
}