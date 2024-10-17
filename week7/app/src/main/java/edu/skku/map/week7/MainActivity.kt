package edu.skku.map.week7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXT_NAME = "extra_key_name"
        const val EXT_AGE = "extra_key_age"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNewActivity = findViewById<Button>(R.id.buttonNewActivity)
        btnNewActivity.setOnClickListener {
            val editTextName = findViewById<EditText>(R.id.editTextName)
            val editTextAge = findViewById<EditText>(R.id.editTextAge)

            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toInt()

            val intent = Intent(this, MainActivity2::class.java).apply{
                putExtra(EXT_NAME, name)
                putExtra(EXT_AGE, age)
            }
            startActivity(intent)
//            Toast.makeText(applicationContext, "${name} ${age}", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onResume() {
        super.onResume()
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)

        editTextName.text.clear()
        editTextAge.text.clear()
    }
}