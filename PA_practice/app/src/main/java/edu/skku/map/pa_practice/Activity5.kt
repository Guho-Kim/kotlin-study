package edu.skku.map.pa_practice

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_DATE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_PEOPLE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_NAME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_OPENING_HOURS
import edu.skku.map.pa_practice.model.Reservation
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Activity5 : AppCompatActivity() {
    private var selectedTime: LocalTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
        val restaurantName = findViewById<TextView>(R.id.restaurant_name)
        val numberOfPeople = findViewById<TextView>(R.id.number_of_people)
        val openingHours = findViewById<TextView>(R.id.opening_hours)
        val cancelBtn = findViewById<Button>(R.id.cancel_btn)
        val confirmBtn = findViewById<Button>(R.id.confirm_btn)
        val editReservationTime = findViewById<EditText>(R.id.reservation_time)


        restaurantName.text = intent.getStringExtra(EXT_RESTAURANT_NAME)
        numberOfPeople.text = intent.getStringExtra(EXT_RESERVATION_PEOPLE)
        openingHours.text = intent.getStringExtra(EXT_RESTAURANT_OPENING_HOURS)




        editReservationTime.setOnClickListener {
            selectTime { time ->
                selectedTime = time
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                editReservationTime.setText(time.format(formatter)) // 선택된 시간을 EditText에 설정
            }
        }

        val dateString = intent.getStringExtra(EXT_RESERVATION_DATE)
        val reservationDate = findViewById<TextView>(R.id.reservation_date)
        reservationDate.text = dateString


        cancelBtn.setOnClickListener {
            finish()
        }

        confirmBtn.setOnClickListener {
            val loginPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
            val user_id = loginPref.getString("userId", null).toString()

            val sharedPref = getSharedPreferences("MyReservationPrefs_$user_id", MODE_PRIVATE)
            val editor = sharedPref.edit()
            val gson = Gson()
            val json = sharedPref.getString("reservations", null)
            val type = object : com.google.gson.reflect.TypeToken<MutableList<Reservation>>() {}.type
            val reservationList: MutableList<Reservation> = gson.fromJson(json, type) ?: mutableListOf()
            val selectedDate = intent.getStringExtra(EXT_RESERVATION_DATE)
            // 새 예약 데이터 추가
            val newReservation = Reservation(
                userId = user_id,
                restaurantName = restaurantName.text.toString(),
                numberOfPeople = numberOfPeople.text.toString().toIntOrNull() ?: 0,
                reservationDate = selectedDate.toString(),
                reservationTime = selectedTime.toString()
            )
            reservationList.add(newReservation)

            // 리스트를 JSON 문자열로 변환
            val updatedJson = gson.toJson(reservationList)

            // SharedPreferences에 저장
            editor.putString("reservations", updatedJson)
            editor.apply()


            val intent = Intent(this@Activity5, Activity1::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()

        }
    }


    fun selectTime(onTimeSelected: (LocalTime) -> Unit) {
        val now = LocalTime.now()

        // 시간 선택 다이얼로그
        TimePickerDialog(this, { _, hourOfDay, minute ->
            val time = LocalTime.of(hourOfDay, minute)
//            if (selectedDate == LocalDate.now() && time.isBefore(now)) {
//                Toast.makeText(this, "예약 시간은 현재 시간 이후여야 합니다.", Toast.LENGTH_SHORT).show()
//            } else {
//                onTimeSelected(time) // 유효한 시간 선택 후 콜백 실행
//            }
            onTimeSelected(time) // 유효한 시간 선택 후 콜백 실행
        }, now.hour, now.minute, true).show() // true for 24시간 형식
    }
}