package com.example.baicuoiky

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_speak.*
import java.util.*

class speakActivity : AppCompatActivity() {
    private lateinit var list1:ArrayList<MyModel>
    private val RQ_SPEECH_REC = 102
    var random:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speak)
        list1 = intent.getSerializableExtra("data1") as ArrayList<MyModel>
        random = (0..list1.size).random()
        tvText.text = list1[random].defines


        btnButton.setOnClickListener{
            askSpeechInput()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RQ_SPEECH_REC && resultCode== Activity.RESULT_OK){
            list1 = intent.getSerializableExtra("data1") as ArrayList<MyModel>
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if(result?.get(0).toString().equals(list1[random].defines)){
                SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Chúc mừng")
                    .setContentText("Bạn đã trả lời đúng!")
                    .setConfirmClickListener {
                        onBackPressed()
                        it.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun askSpeechInput(){
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something")
            startActivityForResult(i,RQ_SPEECH_REC)

    }
}