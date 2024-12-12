package edu.skku.map.pa_practice

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_DATE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_PEOPLE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_TIME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_NAME
import edu.skku.map.pa_practice.model.Reservation
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
            val loginPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
            val user_id = loginPref.getString("userId", null).toString()

            val sharedPref = getSharedPreferences("MyReservationPrefs_$user_id", MODE_PRIVATE)
            val editor = sharedPref.edit()
            val gson = Gson()
            val json = sharedPref.getString("reservations", null)
            val type = object : com.google.gson.reflect.TypeToken<MutableList<Reservation>>() {}.type
            val reservationList: MutableList<Reservation> = gson.fromJson(json, type) ?: mutableListOf()

            val selectedDate = intent.getStringExtra(EXT_RESERVATION_DATE) // 삭제하려는 예약의 날짜
            val selectedTime = intent.getStringExtra(EXT_RESERVATION_TIME) // 삭제하려는 예약의 시간
            val selectedRestaurantName = intent.getStringExtra(EXT_RESTAURANT_NAME)

            // 조건에 맞는 예약 삭제
            val iterator = reservationList.iterator()
            while (iterator.hasNext()) {
                val reservation = iterator.next()
                if (reservation.reservationDate == selectedDate && reservation.reservationTime == selectedTime && selectedRestaurantName == reservation.restaurantName) {
                    iterator.remove()
                }
            }

            // 리스트를 JSON 문자열로 변환
            val updatedJson = gson.toJson(reservationList)

            // SharedPreferences에 저장
            editor.putString("reservations", updatedJson)
            editor.apply()

            Toast.makeText(this, "예약이 삭제되었습니다.", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}