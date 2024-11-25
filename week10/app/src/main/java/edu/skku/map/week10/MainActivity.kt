package edu.skku.map.week10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                textView.text = longTask().await()
            }
        }
    }

    fun longTask() = CoroutineScope(Dispatchers.Default).async {
        var totalCount = 0
        var innerCount = 0
        for (bigLoop in 1..100) {
            for (smallLoop in 1..1_000_000) {
                // Generate random x and y values between 0 and 1
                val x = Math.random()
                val y = Math.random()

                // Check if the point is within the circle
                if (x * x + y * y <= 1) innerCount += 1
                totalCount += 1
            }
            val currentValue = innerCount.toFloat() / totalCount * 4.0f
            CoroutineScope(Dispatchers.Main).launch {
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = "Done ${bigLoop}%...\n" +
                        "Current estimation: ${String.format("%.6f", currentValue)}"
            }
        }
        val lastValue = innerCount.toFloat() / totalCount * 4.0f
        "Done!\nEstimation: ${String.format("%.6f", lastValue)}"
    }
}
