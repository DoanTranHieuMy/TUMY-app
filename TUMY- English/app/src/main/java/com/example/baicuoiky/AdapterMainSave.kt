package com.example.baicuoiky

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.card_item.view.*
import kotlinx.android.synthetic.main.term_item.view.*

class AdapterMainSave(private val context:Context, private val myModelArrayList: ArrayList<modelMain>): PagerAdapter() {
    lateinit var mTTs: TextToSpeech
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return myModelArrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.term_item, container, false)

        val model = myModelArrayList[position]
        val title = model.title
        val count = model.count
        val name = model.name



        view.TVtitle.text = title
        view.countTerms.text = count.toString()+" thuật ngữ"
        view.name.text = name

        view.setOnClickListener {
            val i = Intent(context,term::class.java)
            i.putExtra("data",title)
            i.putExtra("IDdata" , name)
            context.startActivity(i)
        }


        container.addView(view, position)

        return view

    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}