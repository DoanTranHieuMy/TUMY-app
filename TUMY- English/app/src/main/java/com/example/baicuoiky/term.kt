package com.example.baicuoiky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_class.*
import kotlinx.android.synthetic.main.activity_term.*
import kotlinx.android.synthetic.main.list_item.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class term : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private lateinit var actionBar:ActionBar
    private lateinit var myModelList: ArrayList<MyModel>
    private lateinit var listTes: ArrayList<MyModel>
    private lateinit var myAdapter: Adapter
    private lateinit var list:ArrayList<MyModel>
    private lateinit var auth: FirebaseAuth
    var nameTerm: String? = ""
    var id:String? = ""
    var arr :ArrayList<MyModel> = ArrayList()

    init{
        db = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term)
        auth = FirebaseAuth.getInstance()
        getValue()
    }

    private fun getValue() {
        val intent = intent
        nameTerm= intent.getStringExtra("data")
        id = intent.getStringExtra("IDdata")
        if(id == null){
            id = auth.uid.toString()
        }
        nameTerm1.text = nameTerm.toString()
        myModelList = ArrayList()
        db.child(id.toString()).child(nameTerm.toString()).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    var a:String = i.child("defines").getValue().toString()
                    var b:String = i.child("terms").getValue().toString()
                    myModelList.add(
                        MyModel(a,b)
                    )
                    myAdapter = Adapter(this@term, myModelList)

                    viewPager.adapter = myAdapter

                    viewPager.setPadding(10,0,100,0)
                    recycler_view.adapter = AdapterReading(myModelList)
                    recycler_view.layoutManager = LinearLayoutManager(this@term)
                    recycler_view.setHasFixedSize(true)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun study(view: View){
        val intent: Intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("data1", myModelList)
        startActivity(intent)
    }
    fun speak(view:View){
        val intent:Intent = Intent(this,speakActivity::class.java)
        intent.putExtra("data1", myModelList)
        startActivity(intent)
    }
    fun cardMaching(view:View){
        val i :Intent = Intent(this,Card_Maching_Activity::class.java)
        i.putExtra("data" , nameTerm)
        startActivity(i)
    }
    fun test(view:View){
        var i:Int = 0
        listTes = myModelList
        while(i<listTes.size){
            var random = (i..listTes.size-1).random()
            val aa = listTes[i]
            listTes[i] = listTes[random]
            listTes[random] = aa
            i++
        }
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("data1" , listTes)
        startActivity(intent)
    }

}