package com.example.a7workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_excercise.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.util.*

class ExcerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? = null;
    private var restProgress = 0
    private var restTimerDuratin: Long = 1

    private var exerciseTimer: CountDownTimer? = null;
    private var excerciseProgress = 0
    private var excerciseTimerDiration: Long = 1

    private var excerciseList: ArrayList<ExcerciseModel>? = null
    private var currentPosition = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var excerciseAdapater: ExcerciseStatusAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise)

        setSupportActionBar(toolbar_excercise_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_excercise_activity.setNavigationOnClickListener {
            customDialog()
        }
        tts = TextToSpeech(this, this)
        excerciseList = Constants.defaultExcerciseLis()
        setUpRestView()
        setUpexcerciseStatusRecyclerView()
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            excerciseProgress = 0
        }
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        if (player != null) {
            player!!.stop();
        }
        super.onDestroy()
    }

    private fun setRestProgressBar() {
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuratin * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentPosition++
                excerciseList!![currentPosition].setIsselected(true)
                excerciseAdapater!!.notifyDataSetChanged()
                setUpExcerciseView()
            }
        }.start()
    }

    private fun setUpRestView() {
        try {
            val soundURI =
                Uri.parse("android:resource://com.example.a7workoutapp/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player!!.isLooping = false
            player!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        ll_Restview.visibility = View.VISIBLE
        ll_Excerciseview.visibility = View.GONE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        tv_upcoming_excercise.text = excerciseList!![currentPosition + 1].getName()
        setRestProgressBar()
    }

    private fun setExcerciseProgressBar() {
        progressBarExcercise.progress = excerciseProgress
        exerciseTimer = object : CountDownTimer(excerciseTimerDiration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                excerciseProgress++
                progressBarExcercise.progress = 30 - excerciseProgress
                tvTimerExcercise.text =
                    (excerciseTimerDiration.toInt() - excerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentPosition < excerciseList!!.size - 1) {
                    excerciseList!![currentPosition].setIsselected(false)
                    excerciseList!![currentPosition].setIsCompleted(true)
                    excerciseAdapater!!.notifyDataSetChanged()
                    setUpRestView()
                } else {
                    finish()
                    val intent = Intent(this@ExcerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setUpExcerciseView() {
        ll_Restview.visibility = View.GONE
        ll_Excerciseview.visibility = View.VISIBLE
        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            excerciseProgress = 0
        }
        setExcerciseProgressBar()
        speakOut(excerciseList!![currentPosition].getName())
        tv_excerciseName.text = excerciseList!![currentPosition].getName()
        gif_image.setImageResource(excerciseList!![currentPosition].getImage())
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.i("TTS", "The anguage specified is not supported")
            }
        } else {
            Log.i("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun setUpexcerciseStatusRecyclerView() {
        rvexcerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        excerciseAdapater = ExcerciseStatusAdapter(excerciseList!!, this)
        rvexcerciseStatus.adapter = excerciseAdapater
    }

    private fun customDialog(){
        val customDialog=Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)
        customDialog.btYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.btNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
}