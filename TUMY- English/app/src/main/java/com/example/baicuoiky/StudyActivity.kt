package com.example.baicuoiky

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_study.*

class StudyActivity : AppCompatActivity() {
    private lateinit var list1:ArrayList<MyModel>
    private lateinit var list2:ArrayList<MyModel>
    var count:Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)
        show()
    }
    private fun show(){
        list2 =  intent.getSerializableExtra("data1") as ArrayList<MyModel>
        if(intent!=null){
            list1 = intent.getSerializableExtra("data1") as ArrayList<MyModel>
            tvTerms.text = list1[0].terms
        }else{
            tvTerms.text = list1[0].terms
            for(i in 0 until list1.size-1){
                list1[i]= list1[i+1]
            }
        }
    }
    fun check(view:View){
        if(list1[0].defines.equals(list2[list2.size-1].defines) == true) {
            if (tvDefines1.text.toString().equals(list1[0].defines) == true) {
                count++
                var point:Float = ((10.toFloat()/list2.size.toFloat())*count.toFloat())
                SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Chúc mừng")
                        .setContentText("Bạn đã trả lời đúng!")
                        .setConfirmClickListener {
                            it.dismiss()
                            SweetAlertDialog(this)
                                    .setTitleText("Chúc mừng")
                                    .setContentText("Điểm số của bạn là "+point)
                                    .setConfirmClickListener {
                                        onBackPressed()
                                    }
                                    .show()
                        }
                        .show()
            } else {
                var point:Float = ((10.toFloat()/list2.size.toFloat())*count.toFloat())
                SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Sai rồi")
                        .setContentText("Đáp án là " + list1[0].defines + "!! điểm số của bạn là " + point)
                        .setConfirmClickListener {
                            onBackPressed()
                        }
                        .show()
            }
        }else{
            if(tvDefines1.text.toString().equals(list1[0].defines)== true){
                count++
                SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Chúc mừng")
                        .setContentText("Bạn đã trả lời đúng!")
                        .setConfirmClickListener {
                            show()
                            it.dismiss()
                        }
                        .show()
                for(i in 0 until list1.size-1){
                    list1[i]= list1[i+1]
                }
            }else{
                SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Sai rồi")
                        .setContentText("Đáp án là "+list1[0].defines)
                        .setConfirmClickListener {
                            show()
                            it.dismiss()
                        }
                        .show()
                for(i in 0 until list1.size-1){
                    list1[i]= list1[i+1]
                }
            }
        }


    }
}