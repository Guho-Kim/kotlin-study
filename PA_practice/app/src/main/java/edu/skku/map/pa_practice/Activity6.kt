package edu.skku.map.pa_practice

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_DATE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_PEOPLE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_TIME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_NAME
import edu.skku.map.pa_practice.utils.logoUtil

class Activity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)


        val numberOfPeople = findViewById<TextView>(R.id.number_of_people)
        val reservationTime = findViewById<TextView>(R.id.reservation_time)
        val reservationDate = findViewById<TextView>(R.id.reservation_date)
        val restaurantName = findViewById<TextView>(R.id.restaurant_name)
        val restaurantLogo = findViewById<ImageView>(R.id.reservation_logo)

        numberOfPeople.text = intent.getStringExtra(EXT_RESERVATION_PEOPLE).toString()
        reservationTime.text = intent.getStringExtra(EXT_RESERVATION_TIME)
        reservationDate.text = intent.getStringExtra(EXT_RESERVATION_DATE)
        restaurantName.text = intent.getStringExtra(EXT_RESTAURANT_NAME)


        val logoResource = logoUtil.getLogoResource(restaurantName.text.toString())
        restaurantLogo.setImageResource(logoResource)

        val cancelBtn = findViewById<Button>(R.id.cancel_btn)
        cancelBtn.setOnClickListener {
            finish()
        }
    }
}