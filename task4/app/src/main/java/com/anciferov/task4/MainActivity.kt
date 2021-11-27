package com.anciferov.task4

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val url: String = "https://sun9-46.userapi.com/impg/Hj646DIin87E9FHXsZOpkXZfKbPuAn0KbzumHQ/-znLKZ9Ca_M.jpg?size=2160x2160&quality=96&sign=4f755b4e0be56ed1c09b2cc1cd216481&type=album"
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img = findViewById(R.id.imageView)
        findViewById<Button>(R.id.button)?.setOnClickListener {
            Log.i("TEST", "Button pressed")
            Picasso.get().load(url).into(img);
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
        super.onResume()
        Log.i("TEST", "On Resume")
        Log.i("TEST", "Number of threads: " + Thread.getAllStackTraces().size)
        super.onResume()
    }
}