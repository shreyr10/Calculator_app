package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {

    var qlist: ArrayList<Questions>? = null            //so that list can be accessed from all the functions
    var currentQuesPos = 1
    var selectedQuesPos = 0                            //can have values -1, 0 and (1,2,3,4) for a selected option
    private var numCorrectAnswers = 0
    private var username : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)   //Here we are viewing activity_quiz_questions

        username = intent.getStringExtra(Constants.USER_NAME)

        qlist = Constants.getQuestions()
        displayQues()

        /* WHEN ANY OF THE OPTIONS ARE SELECTED
           ALL TEXTVIEWS ARE SET TO DEFAULT AND THE SELECTED TEXTVIEW IS SET TO ANOTHER SETTING

           We are checking for selectedQuesPos at every click so that user cannot change the answer
           after clicking the submit button (-1 is the value when we are supposed to move to the next question)
         */

        option_One.setOnClickListener {
            if(selectedQuesPos!=-1) {
                defaultTv()
                selectTv(option_One)
                selectedQuesPos = 1
            }
        }
        option_Two.setOnClickListener {
            if(selectedQuesPos!=-1) {
                defaultTv()
                selectTv(option_Two)
                selectedQuesPos = 2
            }
        }
        option_Three.setOnClickListener {
            if(selectedQuesPos!=-1) {
                defaultTv()
                selectTv(option_Three)
                selectedQuesPos = 3
            }
        }
        option_Four.setOnClickListener {
            if(selectedQuesPos!=-1) {
                defaultTv()
                selectTv(option_Four)
                selectedQuesPos = 4
            }
        }

        btn_submit.setOnClickListener {
            if(selectedQuesPos == 0)                          //means user is clicking submit without selecting an option
                Toast.makeText(this,"Please select an option",Toast.LENGTH_SHORT).show()

            else if(selectedQuesPos == -1){                  //means user is clicking submit for going to the next question
                btn_submit.text = "Submit"

                if(currentQuesPos== qlist!!.size){
                    val intent = Intent(this, ResultActivity::class.java )
                    intent.putExtra(Constants.USER_NAME,username)
                    intent.putExtra(Constants.CORRECT_ANS, numCorrectAnswers)
                    intent.putExtra(Constants.TOTAL_QUES, qlist!!.size)
                    startActivity(intent)
                }
                else {
                    currentQuesPos++
                    displayQues()
                }
            }

            else {                                      //means user is clicking submit after selecting an option
                val currQues = qlist!!.get(currentQuesPos-1)

                if(selectedQuesPos == currQues.correctAnswer) {
                    numCorrectAnswers++
                    applyAnswerView(selectedQuesPos, R.drawable.textview_correct_bg)
                }

                else {
                    applyAnswerView(selectedQuesPos, R.drawable.textview_incorrect_bg)
                    applyAnswerView(currQues.correctAnswer, R.drawable.textview_correct_bg)
                }

                if(currentQuesPos== qlist!!.size)
                    btn_submit.text = "FINISH"
                else
                    btn_submit.text = "NEXT QUESTION"

                selectedQuesPos = -1                 //setting the value to -1 so as to go to the next question

            }

        }
    }


    //here we are applying the drawable(correct/incorrect) on the passed textview value
    fun applyAnswerView(ans: Int, drawable: Int) {

        when(ans) {

            1 ->  {option_One.background = ContextCompat.getDrawable(this, drawable) }
            2 ->  {option_Two.background = ContextCompat.getDrawable(this, drawable)}
            3 ->  {option_Three.background = ContextCompat.getDrawable(this, drawable)}
            4 ->  {option_Four.background = ContextCompat.getDrawable(this, drawable)}

        }
    }

    fun displayQues() {

        defaultTv()                  //setting default textview every time next question appears
        selectedQuesPos = 0

        val q = qlist!![currentQuesPos-1]
        option_One.text = q.optionOne
        option_Two.text = q.optionTwo
        option_Three.text = q.optionThree
        option_Four.text = q.optionFour
        image.setImageResource(q.image)
        progess_bar.progress = 1
        progress_tv.text = "$currentQuesPos / ${qlist!!.size}"
        progess_bar.progress = currentQuesPos
        question_tv.text = q.question
    }

    // here we are applying the default setting to each textview
    fun defaultTv() {

        var tvList = ArrayList<TextView>()

        tvList.add(0,option_One)
        tvList.add(1,option_Two)
        tvList.add(2,option_Three)
        tvList.add(3,option_Four)

        for(i in tvList){
            i.background = ContextCompat.getDrawable(this,R.drawable.textview_border_bg)   //setting the textview background
            i.setTextColor(Color.parseColor("#7A8089"))                                //the text color of the text when not selected(default)
            i.typeface = Typeface.DEFAULT
        }
    }

    // here we are applying a different setting to textview which is selected
    fun selectTv(tv: TextView) {

        tv.background = ContextCompat.getDrawable(this,R.drawable.textview_selected_border_bg)
        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
    }
}