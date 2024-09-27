package edu.skku.map.week3

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        var counter = 0
        val textview = findViewById<TextView>(R.id.testView)
        val imageview = findViewById<ImageView>(R.id.imageView)
        val left_btn = findViewById<Button>(R.id.button)
        val right_btn = findViewById<Button>(R.id.button2)

        textview.text = "pizza"
        imageview.setImageResource(R.drawable.pizza)

        left_btn.setOnClickListener {
            if(counter == 0) {
                Toast.makeText(this@MainActivity, "First Image", Toast.LENGTH_LONG).show()
            }else if(counter == 1){
                textview.text = "pizza"
                imageview.setImageResource(R.drawable.pizza)
                counter--;
            }else if(counter == 2){
                textview.text = "chicken"
                imageview.setImageResource(R.drawable.chicken)
                counter--;
            }else{
                textview.text = "hamburger"
                imageview.setImageResource(R.drawable.hamburger)
                counter--;
            }
        }

        right_btn.setOnClickListener {
            if(counter == 0){
                textview.text = "chicken"
                imageview.setImageResource(R.drawable.chicken)
                counter++;
            }else if(counter == 1){
                textview.text = "hamburger"
                imageview.setImageResource(R.drawable.hamburger)
                counter++;
            }else if(counter == 2){
                textview.text = "ramen"
                imageview.setImageResource(R.drawable.ramen)
                counter++;
            }else{
                Toast.makeText(this@MainActivity, "Last Image", Toast.LENGTH_LONG).show()
            }
        }
    }
}