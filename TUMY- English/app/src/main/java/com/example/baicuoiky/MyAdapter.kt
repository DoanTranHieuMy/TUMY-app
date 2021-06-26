package com.example.baicuoiky

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyAdapter(var context:Context, var arr: ArrayList<MyModel>) : BaseAdapter() {
    class ViewHolder(row: View){
        var text1: TextView
        var text2: TextView
        init {
            text1 = row.findViewById(R.id.textView_terms) as TextView
            text2 = row.findViewById(R.id.textView_defines) as TextView
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder : ViewHolder
        if(convertView==null){
            var LayoutInflater : LayoutInflater = LayoutInflater.from(context)
            view = LayoutInflater.inflate(R.layout.row_add_cricketer,null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        var data: MyModel = getItem(position) as MyModel
        viewHolder.text1.text = data.terms
        viewHolder.text2.text = data.defines
        return  view as View
    }

    override fun getItem(position: Int): Any {
        return arr.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arr.size
    }
}