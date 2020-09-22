package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastDigIsNum = false
    var dotOccuredBefore = false      //avoids 2 decimal points in the same value

    var operatorOccuredBefore = false     //so that only one operation can be performed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
       TvInput.append((view as Button).text)
        lastDigIsNum = true
    }

    fun onClear(view: View) {
        TvInput.setText("")
        lastDigIsNum = false                    //resets all the variables
        dotOccuredBefore = false
        operatorOccuredBefore = false
    }

    fun onDecimal(view: View) {
        if(lastDigIsNum && !dotOccuredBefore) {
            TvInput.append(".")
            dotOccuredBefore = true
            lastDigIsNum = false
        }
    }

    fun onOperator(view: View) {
        if (lastDigIsNum && !operatorOccuredBefore) {
            TvInput.append((view as Button).text)
            dotOccuredBefore = false           //so that decimal point can be placed in the next value after operator
            operatorOccuredBefore = true
            lastDigIsNum = false
        }
    }

    fun onEqual(view: View) {
        if(lastDigIsNum)
        {
            var prefix = false
            var exp = TvInput.text.toString()

            if(exp.startsWith("-"))         //if the number is in -
            {
                prefix=true
                exp = exp.substring(1)     //omit the - prefix so that split does not consider it
            }

            if(exp.contains("-"))
            {
                var split = exp.split("-")

                var one = split[0]         //first number
                var two = split[1]         //second number

                if(prefix)
                    one = "-" + one       //adding - sign before the first number

                TvInput.text = remZero((one.toDouble() - two.toDouble()).toString())

            }
            else if(exp.contains("+"))
            {
                var split = exp.split("+")

                var one = split[0]
                var two = split[1]

                if(prefix)
                    one = "-" + one

                TvInput.text = remZero((one.toDouble() + two.toDouble()).toString())

            }
            else if(exp.contains("*"))
            {
                var split = exp.split("*")

                var one = split[0]
                var two = split[1]

                if(prefix)
                    one = "-" + one

                TvInput.text = remZero((one.toDouble() * two.toDouble()).toString())

            }
            else if(exp.contains("/"))
            {
                var split = exp.split("/")

                var one = split[0]
                var two = split[1]

                if(prefix)
                    one = "-" + one

                TvInput.text = remZero((one.toDouble() / two.toDouble()).toString())

            }

            lastDigIsNum = true
            operatorOccuredBefore = false
            dotOccuredBefore = false
        }
    }

    fun remZero(result: String) : String {           //removing unnecessary .0 after every calculation
        var value = result
        if(value.contains(".0"))
            value = value.substring(0,value.length-2)

        return value
    }
}