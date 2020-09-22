package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)      //here we are viewing the contents of activity_main

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN    //so that the notification bar does not appear

        startBtn.setOnClickListener {

            if (nameField.text.toString().isEmpty())
                Toast.makeText(this,"Please Enter a Name",Toast.LENGTH_SHORT).show()

            else {

                val i = Intent(this,QuizQuestionsActivity::class.java)
                i.putExtra(Constants.USER_NAME, nameField.text.toString())
                startActivity(i)                      //we move to the activity QuizQuestionsActivity
                finish()
            }

        }
    }
}