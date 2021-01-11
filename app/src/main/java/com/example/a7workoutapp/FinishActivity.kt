package com.example.a7workoutapp

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.a7workoutapp.db.SqliteopenHelper
import kotlinx.android.synthetic.main.activity_finish.*
import java.util.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        setSupportActionBar(toolbar_finish_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_finish_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        btn_finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addDatetoDataBase()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addDatetoDataBase() {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.i("Date", "Date:  " + dateTime);

        val sdf = SimpleDateFormat("dd MM yyyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        val dbHandler = SqliteopenHelper(this, null)
        dbHandler.addDate(date)
        Log.i("Date: ", "Added")
    }
}