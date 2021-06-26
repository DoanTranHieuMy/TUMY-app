package com.example.baicuoiky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_card_maching.*
import kotlinx.android.synthetic.main.activity_term.*

class Card_Maching_Activity : AppCompatActivity() {
    private lateinit var arr1:ArrayList<String>
    private lateinit var arr2:ArrayList<String>
    private lateinit var auth: FirebaseAuth
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: DatabaseReference
    private lateinit var adapter:AdapterCardMaching
    var pauseOfSet = 0
    var nameTerm: String? = ""
    var i:Int = 0
    var random:Int = 0
    init{
        db = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_maching)
        auth = FirebaseAuth.getInstance()
        chronomter.base = SystemClock.elapsedRealtime() - pauseOfSet
        chronomter.start()
        getValue()
    }
    fun gettime(view:View){
        chronomter.stop()
        var ofSet :Long = SystemClock.elapsedRealtime()-chronomter.base
        var s:Int = ofSet.toInt()/1000
        var milis:Int = ofSet.toInt()%1000
        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
            .setTitleText("Chúc mừng")
            .setContentText("Thời gian của bạn là " + s+","+milis)
            .setConfirmClickListener {
                onBackPressed()
                it.dismiss()
            }
            .show()
    }
    private fun getValue(){
        nameTerm= intent.getStringExtra("data")
        arr1 = ArrayList()
        arr2 = ArrayList()
        db.child(auth.uid.toString()).child(nameTerm.toString()).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    var a:String = i.child("defines").getValue().toString()
                    var b:String = i.child("terms").getValue().toString()
                    arr1.add(
                        a
                    )
                    arr1.add(
                        b
                    )
                    arr2.add(
                        a
                    )
                    arr2.add(
                        b
                    )
                }
                while(i<arr2.size){
                    random = (i..arr2.size-1).random()
                    var aa : String = arr2[i]
                    arr2[i] = arr2[random]
                    arr2[random] = aa
                    i++
                }
                gridView.adapter = AdapterCardMaching(this@Card_Maching_Activity, arr2 , arr1)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}