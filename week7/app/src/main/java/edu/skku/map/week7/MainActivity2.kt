package edu.skku.map.week7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.skku.map.week7.MainActivity.Companion.EXT_AGE
import edu.skku.map.week7.MainActivity.Companion.EXT_NAME

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra(MainActivity.EXT_NAME)
        val age = intent.getIntExtra(MainActivity.EXT_AGE, -1)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "You typed name ${name} and age ${age}, is that right?"


        val btnYes = findViewById<Button>(R.id.btnYes)
        val btnNo = findViewById<Button>(R.id.btnNo)

        btnYes.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java).apply{
                putExtra(EXT_NAME, name)
                putExtra(EXT_AGE, age)
            }
            startActivity(intent)
        }

        btnNo.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java).apply{
//            startActivity(intent)
            finish()
        }

    }
}