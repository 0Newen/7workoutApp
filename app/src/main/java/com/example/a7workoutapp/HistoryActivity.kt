package com.example.a7workoutapp

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7workoutapp.db.SqliteopenHelper
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "History "
        }
        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        GetAllDates()
    }

    private fun GetAllDates() {
        val dbHandler = SqliteopenHelper(this, null)
        val allDateList = dbHandler.getAllCompletedDatesList()

        if(allDateList.size>0){
            tv_no_data.visibility=View.INVISIBLE
            recy_history.visibility=View.VISIBLE

            recy_history.layoutManager=LinearLayoutManager(this)
            val historyAdapter=HistoryAdapter(this,allDateList)
            recy_history.adapter=historyAdapter
        }else{
            tv_no_data.visibility=View.VISIBLE
            recy_history.visibility=View.INVISIBLE
        }

    }
}