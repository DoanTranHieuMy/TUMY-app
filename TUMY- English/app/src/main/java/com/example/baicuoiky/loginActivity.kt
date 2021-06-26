package com.example.baicuoiky

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class loginActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private var firstTimeUser = true
    private var fileUri:Uri? = null
    private lateinit var db: DatabaseReference
    init{
        db = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        buttonClicks()


    }

    private fun buttonClicks(){
        btn_login.setOnClickListener{
            firstTimeUser = false
            createOrLoginUser()
        }
        btn_register.setOnClickListener{
            firstTimeUser = true
            createOrLoginUser()
        }
        iv_profileImage.setOnClickListener {
            selectImage()
        }
    }
    private fun createOrLoginUser(){
        val email = et_emailLogin.text.toString()
        val password = et_passwordLogin.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val list: MutableList<String> = ArrayList()
                    db.addValueEventListener(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for(i in snapshot.children){
                                list.add(i.key.toString())
                            }
                            if(list.contains(auth.uid.toString()) == true){
                                val i = Intent(this@loginActivity,MainActivity2::class.java)
                                startActivity(i)
                            }else{
                                val i = Intent(this@loginActivity,MainActivity::class.java)
                                startActivity(i)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })

                } else {
                    Toast.makeText(this@loginActivity, "Đăng nhập bị lỗi" , Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun selectImage(){
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1000,1000)
            .start()
    }
    override fun onActivityResult(requestCode:Int,resultCode:Int,data:Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK->{
                fileUri = data?.data
                iv_profileImage.setImageURI(fileUri)
            }
            ImagePicker.RESULT_ERROR->{
                Toast.makeText(this,ImagePicker.getError(data) , Toast.LENGTH_SHORT).show()
            }
            else->{
                Toast.makeText(this,"Task cancelled" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}