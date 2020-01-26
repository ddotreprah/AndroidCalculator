package com.example.a7_p1_harper_todd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

//For this app I used a tutorial located at: https://www.youtube.com/watch?v=EpP6KgJtHTk
//each segment of coded is commented with implementation notes
//I modified styles.xml like the tutorial and did not change it because I thought it looked really good

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //numbers
        tvOne.setOnClickListener{ appendOnExpression("1", true) }
        tvTwo.setOnClickListener{ appendOnExpression("2", true) }
        tvThree.setOnClickListener{ appendOnExpression("3", true) }
        tvFour.setOnClickListener{ appendOnExpression("4", true) }
        tvFive.setOnClickListener{ appendOnExpression("5", true) }
        tvSix.setOnClickListener{ appendOnExpression("6", true) }
        tvSeven.setOnClickListener{ appendOnExpression("7", true) }
        tvEight.setOnClickListener{ appendOnExpression("8", true) }
        tvNine.setOnClickListener{ appendOnExpression("9", true) }
        tvDot.setOnClickListener{ appendOnExpression(".",true) }
        tvZero.setOnClickListener{ appendOnExpression("0",true) }


        //operators
        tvAdd.setOnClickListener{ appendOnExpression("+",false) }
        tvSubtract.setOnClickListener{ appendOnExpression("-",false) }
        tvMultiply.setOnClickListener{ appendOnExpression("*",false) }
        tvDivide.setOnClickListener{ appendOnExpression("/",false) }
        tvOpen.setOnClickListener{ appendOnExpression("(",false) }
        tvClose.setOnClickListener{ appendOnExpression(")",false) }

        //clear text view
        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        //back button
        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if( string.isNotEmpty() ) {
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        //equals button
        tvEquals.setOnClickListener {

            //using expression builder to convert to an expression and also unlock
            //functions such as evaluate() to do the work for us
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }
            catch(e:Exception){
                Log.d("Exception", "message" + e.message)
            }

        }


    }


    //function to display buttons pressed
    fun appendOnExpression(string: String, canClear : Boolean) {

        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }

        if(canClear){
            tvResult.text = ""
            tvExpression.append(string)
        }
        else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }

}

