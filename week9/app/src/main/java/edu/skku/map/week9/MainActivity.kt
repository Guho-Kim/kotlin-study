package edu.skku.map.week9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXT_TIME = "extra_key_time"
        const val EXT_DESC = "extra_key_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)


        val editTextTime = findViewById<EditText>(R.id.editTextTime)
        val editTextDesc = findViewById<EditText>(R.id.editTextDesc)



        button.setOnClickListener {
            val time = editTextTime.text.toString()
            val desc = editTextDesc.text.toString()

            val intent = Intent(this, AlarmActivity::class.java).apply{
                putExtra(EXT_TIME, time)
                putExtra(EXT_DESC, desc)
            }
            startActivity(intent)

        }

    }

    override fun onResume() {
        super.onResume()
        val editTextTime = findViewById<EditText>(R.id.editTextTime)
        val editTextDesc = findViewById<EditText>(R.id.editTextDesc)

        editTextTime.text.clear()
        editTextDesc.text.clear()
    }
}