package com.example.baicuoiky

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.card_item.*
import kotlinx.android.synthetic.main.card_item.view.*

class Adapter(private val context: Context, private val myModelArrayList: ArrayList<MyModel>): PagerAdapter() {
    lateinit var front_anim:AnimatorSet
    lateinit var back_anim:AnimatorSet
    var isFront = true
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return myModelArrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false)

        val model = myModelArrayList[position]
        val terms = model.terms
        val description = model.defines

        view.descriptionTv.text = terms
        view.descriptionTv1.text = description
        val scale:Float = context.applicationContext.resources.displayMetrics.density
        view.descriptionTv.cameraDistance = 8000 *scale
        view.descriptionTv1.cameraDistance = 8000 *scale

        front_anim = AnimatorInflater.loadAnimator(context,R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(context,R.animator.back_animator) as AnimatorSet

        view.setOnClickListener{
            if (isFront){
                front_anim.setTarget(view.descriptionTv)
                back_anim.setTarget(view.descriptionTv1)
                front_anim.start()
                back_anim.start()
                isFront = false
            }else{
                front_anim.setTarget(view.descriptionTv1)
                back_anim.setTarget(view.descriptionTv)
                back_anim.start()
                front_anim.start()
                isFront = true
            }
        }


        container.addView(view, position)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}