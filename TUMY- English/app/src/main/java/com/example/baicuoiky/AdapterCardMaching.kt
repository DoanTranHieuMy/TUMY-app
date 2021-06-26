package com.example.baicuoiky

import android.content.Context
import android.graphics.Color
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.card_maching_item.view.*

class AdapterCardMaching(var context: Context, var arr: ArrayList<String> , var arr1:ArrayList<String>): BaseAdapter() {
    private lateinit var list:ArrayList<modelCardMaching>
    private lateinit var chronometer:Chronometer
    var time:Int = 0
    class ViewHolder(row: View){
        var text: TextView
        init {
            text = row.findViewById(R.id.word) as TextView
        }
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View
        var viewHolder : ViewHolder
        var LayoutInflater : LayoutInflater = LayoutInflater.from(context)
        list = ArrayList()
        view = LayoutInflater.inflate(R.layout.card_maching_item,parent,false)
        if(convertView==null){
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        var data: String = getItem(position) as String
        viewHolder.text.text = data
        view.setOnClickListener {
            view.word.setBackgroundColor(Color.parseColor("#ebdcb2"))
            list.add(
                modelCardMaching(data,position,view,parent)
            )
            if(list.size==2){
                var count = arr1.indexOf(list[0].word)
                if(count%2 == 0){
                    if(arr1[count+1].equals(list[1].word)){
                        view.word.alpha = 0F
                        getView(list[0].position,list[0].view,list[0].parent).word.alpha = 0F
                    }else{
                        Toast.makeText(context,"Sai rồi" , Toast.LENGTH_SHORT).show()
                        view.word.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        getView(list[0].position,list[0].view,list[0].parent).word.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    }
                }else{
                    if(arr1[count-1].equals(list[1].word)){
                        view.word.alpha = 0F
                        getView(list[0].position,list[0].view,list[0].parent).word.alpha = 0F
                    }else{
                        Toast.makeText(context,"Sai rồi" , Toast.LENGTH_SHORT).show()
                        view.word.setBackgroundColor(Color.parseColor("#FFFFFF"))
                        getView(list[0].position,list[0].view,list[0].parent).word.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    }
                }
                list.clear()
            }
        }


        return  view as View

    }
}