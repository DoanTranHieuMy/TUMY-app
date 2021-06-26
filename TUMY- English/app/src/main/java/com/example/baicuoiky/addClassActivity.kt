package com.example.baicuoiky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_class.*

class addClassActivity : AppCompatActivity() {

    var arr :ArrayList<MyModel> = ArrayList()
    private lateinit var auth: FirebaseAuth
    var db:DatabaseReference

    init{
        db = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)
        auth = FirebaseAuth.getInstance()
    }
//    fun onDelete(view: View) {
//        parentLinearLayout!!.removeView(view.parent as View)
//    }
    fun lecongtu(){
}
    fun onAddField(view: View) {
        arr.add(MyModel(edit_terms.text.toString(),edit_define.text.toString()))
        listView.adapter = MyAdapter(this,arr)

    }
    fun onBack(view: View){
        onBackPressed()
    }
    fun complete(view: View){

        for(i in 0 until arr.size){
            db.child(auth.uid.toString()).child(nameTerm.text.toString()).child("Từ "+(i+1).toString()).setValue(arr[i])
        }

        val intent:Intent = Intent(this, term::class.java)
        intent.putExtra("data",nameTerm.text.toString())
        startActivity(intent)
    }
}