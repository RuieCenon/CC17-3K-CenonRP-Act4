package com.example.tipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {


    private lateinit var billAmountEditText: EditText
    private lateinit var calculateTipButton: Button
    private lateinit var tipAmountTextView: TextView
    private lateinit var tipPercentageRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        billAmountEditText = findViewById(R.id.billAmountEditText)
        calculateTipButton = findViewById(R.id.calculateTipButton)
        tipAmountTextView = findViewById(R.id.tipAmountTextView)
        tipPercentageRadioGroup = findViewById(R.id.tipPercentageRadioGroup)


        calculateTipButton.setOnClickListener {
            calculateTip()
        }


        billAmountEditText.addTextChangedListener {
            tipAmountTextView.text = ""
        }
    }

    private fun calculateTip() {

        val billAmount = billAmountEditText.text.toString().toDoubleOrNull()


        if (billAmount == null || billAmount <= 0) {
            tipAmountTextView.text = "Please enter a valid bill amount."
            return
        }


        val selectedTipPercentage = when (tipPercentageRadioGroup.checkedRadioButtonId) {
            R.id.tip10 -> 0.10
            R.id.tip15 -> 0.15
            R.id.tip20 -> 0.20
            else -> {
                tipAmountTextView.text = "Please select a tip percentage."
                return
            }
        }


        val tipAmount = billAmount * selectedTipPercentage


        tipAmountTextView.text = String.format("Tip Amount: $%.2f", tipAmount)
    }
}