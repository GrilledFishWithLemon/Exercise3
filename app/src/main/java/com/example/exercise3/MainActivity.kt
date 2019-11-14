package com.example.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = spinnerAge.getItemAtPosition(position)
        Toast.makeText(this,
        "Selected Item=" +selectedItem,
        Toast.LENGTH_LONG)
        .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerAge.onItemSelectedListener=this
        buttonCalculate.setOnClickListener{
            calculatePremium()
        }
        /*val buttonThird: Button
        buttonThird.setOnClickListener(this)*/
    }

    private fun calculatePremium() {
        //get the age group
        val age: Int = spinnerAge.selectedItemPosition

        var premium: Int= when(age){
            0 -> 60
            1 -> 70
            2 -> 90
            3 -> 120
            4 -> 150
            else -> 150
        }
        //get the gender selection
        val gender = radioGroupGender.checkedRadioButtonId
        var genderPaym: Int = 0
        if(gender == R.id.radioButtonMale){
            genderPaym =  when(age){
                0 -> 0
                1 -> 50
                2 -> 100
                3 -> 150
                4 -> 200
                else -> 200
            }
        }else{
            genderPaym = 0
        }

        //determine smoker or non smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        var smokerPaym: Int = 0
        if(smoker){
            smokerPaym =  when(age){
                0 -> 0
                1 -> 100
                2 -> 150
                3 -> 200
                4 -> 250
                else -> 300
        }
        }else  {
            smokerPaym = 0
        }

       val totalPremium: Int = premium + genderPaym + smokerPaym
       textViewPremium.text = "RM " + totalPremium.toString()
    }

    fun reset(view: View) {
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        textViewPremium.text = getString(R.string.insurance_premium)
        checkBoxSmoker.isChecked = false


    }

}
