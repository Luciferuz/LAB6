package ru.spbstu.icc.kspt.lab2.continuewatch

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    lateinit var textSecElapsed: TextView
    lateinit var sharedPref: SharedPreferences
    lateinit var executorService: ExecutorService
    var start: Long = 0
    var end: Long = 0
    var secondsElapsed: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPref = getSharedPreferences("Seconds elapsed", Context.MODE_PRIVATE)
        val newTime: Int = sharedPref.getInt("Seconds elapsed", secondsElapsed)
        textSecElapsed.text = getString(R.string.text_view, newTime)
    }

    override fun onStop() {
        Log.i("TEST","STOPPED")
        end = System.currentTimeMillis()
        executorService.shutdown()
        secondsElapsed += ((end - start)/1000).toInt()
        with(sharedPref.edit()) {
            putInt("Seconds elapsed", secondsElapsed)
            apply()
        }
        super.onStop()
    }

    override fun onStart() {
        Log.i("TEST","STARTED")
        start = System.currentTimeMillis()
        secondsElapsed = sharedPref.getInt("Seconds elapsed", secondsElapsed)
        executorService = Executors.newSingleThreadExecutor()
        executorService.execute {
            while (!executorService.isShutdown) {
                Thread.sleep(1000)
                Log.i("TEST","RUNNING")
                textSecElapsed.post {
                    val newTime = secondsElapsed + ((System.currentTimeMillis() - start)/1000)
                    textSecElapsed.text = getString(R.string.text_view, newTime)
                }
            }
        }
        super.onStart()
    }

}
