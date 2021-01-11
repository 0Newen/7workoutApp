package com.example.a7workoutapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bmi_actvitivy.*
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActvitivy : AppCompatActivity() {

    val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
    val US_UNIT_VIEW = "US_UNIT_VIEW"

    var currentVisibleView: String = METRIC_UNIT_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_actvitivy)

        setSupportActionBar(toolbar_bmi_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "Calculate BMI "
        }
        toolbar_bmi_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        btnCalculateUnits.setOnClickListener {
            if (currentVisibleView.equals(METRIC_UNIT_VIEW)) {
                if (validateMetricUnits()) {
                    val weight: Float = edt_weigth.text.toString().toFloat()
                    val height: Float = edt_height.text.toString().toFloat() / 100
                    val bmi = weight / (height * height)
                    displayBMIResult(bmi)
                } else {
                    Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (validateUSUnits()) {
                    val us_weight: Float = edt_us_weight.text.toString().toFloat()
                    val us_inch_height: String = edt_us_Inch_height.text.toString()
                    val us_feet_height: String = edt_us_feet_height.text.toString()

                    val heigt = us_feet_height.toFloat() + us_inch_height.toFloat()

                    val bmi = 703 * (us_weight / (heigt * heigt))
                    displayBMIResult(bmi)

                } else {
                    Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
                }
            }

        }
        makeVisibleMetrics()
        rdo_group.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_metric) {
                makeVisibleMetrics()
            } else {
                makeVisibleUs()
            }
        }
    }

    private fun makeVisibleMetrics() {
        currentVisibleView = METRIC_UNIT_VIEW
        til_metric_heigth.visibility = View.VISIBLE
        til_metric_weigth.visibility = View.VISIBLE

        edt_weigth.text!!.clear()
        edt_height.text!!.clear()

        til_us_weigth.visibility = View.GONE
        ll_heigth_us_view.visibility = View.GONE

        llDiplayBMIResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleUs() {
        currentVisibleView = US_UNIT_VIEW
        til_metric_heigth.visibility = View.GONE
        til_metric_weigth.visibility = View.GONE

        edt_us_weight.text!!.clear()
        edt_us_Inch_height.text!!.clear()
        edt_us_feet_height.text!!.clear()

        til_us_weigth.visibility = View.VISIBLE
        ll_heigth_us_view.visibility = View.VISIBLE

        llDiplayBMIResult.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String
        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        llDiplayBMIResult.visibility = View.VISIBLE
        /*tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE
         */

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue // Value is set to TextView
        tvBMIType.text = bmiLabel // Label is set to TextView
        tvBMIDescription.text = bmiDescription // Description is set to TextView
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (edt_weigth.text.toString().isEmpty()) {
            isValid = false
        } else if (edt_height.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun validateUSUnits(): Boolean {
        var isValid = true
        when {
            edt_us_weight.text.toString().isEmpty() -> {
                isValid = false
            }
            edt_us_Inch_height.toString().isEmpty() -> {
                isValid = false
            }
            edt_us_feet_height.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }
}