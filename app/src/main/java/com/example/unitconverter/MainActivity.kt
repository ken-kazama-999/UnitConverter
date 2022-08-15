package com.example.unitconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertButton.setOnClickListener { convertUnit() }

    }

    //Function to convert Unit at click of button
    private fun convertUnit() {

        val inTextField = binding.inputEnterValueEditText.text.toString()

        val value = inTextField.toDoubleOrNull()
        if (value == null || value == 0.0) {
            binding.convertResult.text = ""
            return
        }
        // Getting input unit from radio button


        val inputUnit = when (binding.inputUnit.checkedRadioButtonId) {
            R.id.input_mm -> value * 0.001
            R.id.input_cm -> value * 0.01
            R.id.input_m -> value * 1
            else -> value * 1000
        }
        //Getting conversion Unit from radio Button

        val conversionUnit = when (binding.convertUnit.checkedRadioButtonId) {
            R.id.convert_input_mm -> inputUnit * 1000
            R.id.convert_input_cm -> inputUnit * 100
            R.id.convert_input_m -> inputUnit * 1
            else -> inputUnit * 0.001
        }

        //Getting Input as Text to display as result

        val valueFormat = "%.2f".format(value)
        val iuFormat = "%.4f".format(conversionUnit)

        val inputFormat = when (binding.inputUnit.checkedRadioButtonId) {
            R.id.input_mm -> "$valueFormat${"mm"}"
            R.id.input_cm -> "$valueFormat${"cm"}"
            R.id.input_m -> "$valueFormat${"m"}"
            else -> "$valueFormat${"km"}"
        }
        val conversionFormat = when (binding.convertUnit.checkedRadioButtonId) {
            R.id.convert_input_mm -> "mm"
            R.id.convert_input_cm -> "cm"
            R.id.convert_input_m -> "m"
            else -> "km"
        }
        binding.convertResult.text =
            getString(R.string.result, inputFormat, conversionFormat, iuFormat, conversionFormat)
    }

}