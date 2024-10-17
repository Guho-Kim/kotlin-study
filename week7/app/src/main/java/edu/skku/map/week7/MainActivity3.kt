package edu.skku.map.week7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        val name = intent.getStringExtra(MainActivity.EXT_NAME)
        val age = intent.getIntExtra(MainActivity.EXT_AGE, -1)

        Toast.makeText(applicationContext, "Welcome, ${name}(${age} years old)!", Toast.LENGTH_SHORT).show()
    }
}