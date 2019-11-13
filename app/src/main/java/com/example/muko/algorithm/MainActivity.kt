package com.example.muko.algorithm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the widgets
        val currentStringTxt = findViewById<TextView>(R.id.currentTxt_txt)
        val checkBtn = findViewById<Button>(R.id.checkTxt_btn)
        val repeatCountEditTxt = findViewById<EditText>(R.id.repeatCount_editTxt)
        val textEditTxt = findViewById<EditText>(R.id.text_editTxt)

        // Counter to count button click
        var repeatCount: Int?
        var currentStr: String

        // Set a click listener for button widget
        checkBtn.setOnClickListener{
            currentStr = textEditTxt.text.toString()
            repeatCount = repeatCountEditTxt.text.toString().toIntOrNull()
            currentStr = repetitiveCharConvertToStar(currentStr, repeatCount)
            currentStringTxt.text = currentStr
        }

        repeatCountEditTxt.setOnClickListener{
            repeatCountEditTxt.setText("")
        }

        textEditTxt.setOnClickListener{
            textEditTxt.setText("")
        }
    }

    fun repetitiveCharConvertToStar(prm_str: String, prm_repeatCount: Int?): String{

        var convertedStr: String
        var charArray = prm_str.toCharArray()
        var tempChar: Char
        var repetitiveCharCount = 1
        if (prm_repeatCount == null || prm_repeatCount == 0) {
            Toast.makeText(this, getString(R.string.repeat_count_error_1), Toast.LENGTH_LONG).show()
            convertedStr = getString(R.string.repeat_count_error_1)
        } else if (prm_str.isEmpty() || prm_str.equals(getString(R.string.text),true)) {
            Toast.makeText(this, getString(R.string.repeat_count_error_2), Toast.LENGTH_LONG).show()
            convertedStr = getString(R.string.repeat_count_error_2)
        } else{
            tempChar = prm_str[0]
            for(i in 1 until prm_str.length) {
                if(prm_str[i] == tempChar) {
                    repetitiveCharCount++
                } else {
                    if(repetitiveCharCount >= prm_repeatCount) {
                        for(k in 1..repetitiveCharCount ){
                            charArray[i - k] = '*'
                        }
                    }
                    tempChar = prm_str[i]
                    repetitiveCharCount = 1
                }
            }
            if(repetitiveCharCount >= prm_repeatCount) {
                for(i in 1..repetitiveCharCount){
                    charArray[prm_str.length - i] = '*'
                }
            }
            convertedStr = charArray.joinToString("")
        }

        return convertedStr
    }
}
