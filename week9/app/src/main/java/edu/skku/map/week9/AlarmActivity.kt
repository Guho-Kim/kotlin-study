package edu.skku.map.week9

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        val textView = findViewById<TextView>(R.id.textView)
        val btn_ok = findViewById<Button>(R.id.btn_ok)
        val btn_cancle = findViewById<Button>(R.id.btn_cancle)


        val time = intent.getStringExtra(MainActivity.EXT_TIME).toString()
        val desc = intent.getStringExtra(MainActivity.EXT_DESC).toString()

        textView.text = "Do you want to set alarm on the ${time} with description '${desc}'?"

        btn_ok.setOnClickListener {
            val hour = time.split(":")[0].toInt()
            val min = time.split(":")[1].toInt()
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, desc)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, min)
            startActivity(intent)
        }
        btn_cancle.setOnClickListener {

            this.finish()
        }


    }
    override fun onRestart() {
        super.onRestart()
        this.finish()
    }
}