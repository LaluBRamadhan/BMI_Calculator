package com.code

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sf:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor
    private lateinit var weightText:EditText
    private lateinit var heightText:EditText
    private lateinit var calcButton:Button
    private lateinit var index:TextView
    private lateinit var result:TextView
    private lateinit var info:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weightText = findViewById<EditText>(R.id.etWeight)
        heightText = findViewById<EditText>(R.id.etHeight)
        calcButton  = findViewById<Button>(R.id.btnCalculate)
        index = findViewById<TextView>(R.id.tvIndex)
        result = findViewById<TextView>(R.id.tvResult)
        info = findViewById<TextView>(R.id.tvInfo)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()

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
                    result.text = "Gemuk bngt"
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
    override fun onPause() {
        super.onPause()
        val berat = weightText.text.toString().toFloat()
        val tinggi = heightText.text.toString().toFloat()
        editor.apply{
            putFloat("sf_berat",berat)
            putFloat("sf_tinggi",tinggi)
            commit()
        }
    }

    override fun onResume(){
        super.onResume()
        val berat = sf.getFloat("sf_berat",0f)
        val tinggi = sf.getFloat("sf_tinggi", 0f)



        if(berat != 0f){
            weightText.setText(berat.toString())
        }
        if(tinggi != 0f){
            heightText.setText(tinggi.toString())
        }
    }
}

