package com.example.a7workoutapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ll_start.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        tv_start.setTextColor(ContextCompat.getColor(this, R.color.white))
        navigateExcercise()
    }

    private fun navigateExcercise() {
        val intent = Intent(this, ExcerciseActivity::class.java)
        startActivity(intent)
    }

    fun calculateBMI(view: View) {
        val intent = Intent(this, BmiActvitivy::class.java)
        startActivity(intent)
    }

    fun historyActivity(view: View) {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}