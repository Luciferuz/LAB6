package ru.spbstu.icc.kspt.lab2.continuewatch

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    lateinit var textSecElapsed: TextView
    lateinit var sharedPref: SharedPreferences
    lateinit var backgroundTask: Future<*>
    var start: Long = 0
    var end: Long = 0
    var secondsElapsed: Int = 0

    private fun backgroundTask(executorService: ExecutorService) = executorService.submit {
        while (!backgroundTask.isCancelled) {
            Thread.sleep(1000)
            Log.i("TEST","RUNNING")
            textSecElapsed.post {
                val newTime = secondsElapsed + ((System.currentTimeMillis() - start)/1000)
                textSecElapsed.text = getString(R.string.text_view, newTime)
            }
        }
    }

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
        Log.i("TEST", "Number of threads: " + Thread.getAllStackTraces().size)
        backgroundTask.cancel(true)
        end = System.currentTimeMillis()
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
        backgroundTask = backgroundTask((applicationContext as ExecutorClass).executorService)
        super.onStart()
    }

}

class ExecutorClass : Application() {
    val executorService: ExecutorService = Executors.newSingleThreadExecutor()
}
