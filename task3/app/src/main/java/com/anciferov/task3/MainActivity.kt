package com.anciferov.task3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var img: ImageView
    private val viewModel = ViewModelClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img = findViewById(R.id.imageView)
        findViewById<Button>(R.id.button)?.setOnClickListener {
            Log.i("TEST", "Button pressed")
            viewModel.downloadImage()
            viewModel.liveData.observe(this) {
                img.setImageBitmap(viewModel.liveData.value)
            }
        }
    }

    override fun onDestroy() {
        Log.i("TEST", "On Destroy")
        super.onDestroy()
    }

    override fun onPause() {
        Log.i("TEST", "On Pause")
        Log.i("TEST", "Number of threads: " + Thread.getAllStackTraces().size)
        super.onPause()
    }

    override fun onResume() {
        Log.i("TEST", "On Resume")
        Log.i("TEST", "Number of threads: " + Thread.getAllStackTraces().size)
        super.onResume()
    }
}