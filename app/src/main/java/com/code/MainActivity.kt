package com.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton  = findViewById<Button>(R.id.btnCalculate)
        val index = findViewById<TextView>(R.id.tvIndex)
        val result = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if (validateInput(weight, height)) {


                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                val formatedResult = String.format("%.1f", bmi)
                index.text = formatedResult
                if (formatedResult.toFloat() <= 18.5) {
                    result.text = "Kurus"
                    info.text = "<= 18.5"
                } else if (formatedResult.toFloat() >= 18.5 && formatedResult.toFloat() <= 24.9) {
                    result.text = "Normal"
                    info.text = "18.5 - 24.9"
                } else if (formatedResult.toFloat() >= 25.0 && formatedResult.toFloat() <= 29.9) {
                    result.text = "Gemuk"
                    info.text = "25.0 - 29.9"
                } else if (formatedResult.toFloat() >= 30.0) {
                    result.text = "Badan babi"
                    info.text = " >= 30.0"
                }
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Height is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }
}