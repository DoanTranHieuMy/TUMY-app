package com.example.baicuoiky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread

class loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        Handler().postDelayed({
            val intent : Intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        } , 3000)
    }
}