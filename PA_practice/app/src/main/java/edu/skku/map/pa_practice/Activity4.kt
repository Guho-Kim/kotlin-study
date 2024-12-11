package edu.skku.map.pa_practice

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_DATE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_PEOPLE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_NAME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_OPENING_HOURS
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class Activity4 : AppCompatActivity() {

    private var selectedDate: LocalDate? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        val numberOfPeople = findViewById<EditText>(R.id.number_of_people)
        val editReservationDate = findViewById<EditText>(R.id.reservation_date)
        val confirmBtn = findViewById<Button>(R.id.confirm_btn)
        val restaurantName = findViewById<TextView>(R.id.restaurant_name)
        restaurantName.text = intent.getStringExtra(EXT_RESTAURANT_NAME)
        // 날짜 선택
        editReservationDate.setOnClickListener {
            selectDate { date ->
                selectedDate = date
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                editReservationDate.setText(date.format(formatter)) // 선택된 날짜를 EditText에 설정
            }
        }

        // 시간 선택


        confirmBtn.setOnClickListener {
            if(numberOfPeople.text.toString().isBlank() || editReservationDate.text.toString().isBlank()){
                Toast.makeText(this, "필드를 입력하세요.", Toast.LENGTH_SHORT).show()
            }else if(numberOfPeople.text.toString().toInt()>10 ){
                Toast.makeText(this, "10 이하를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this@Activity4, Activity5::class.java).apply {
                    putExtra(EXT_RESERVATION_DATE, selectedDate.toString())
                    putExtra(EXT_RESTAURANT_NAME, intent.getStringExtra(EXT_RESTAURANT_NAME))
                    putExtra(EXT_RESTAURANT_OPENING_HOURS, intent.getStringExtra(EXT_RESTAURANT_OPENING_HOURS))
                    putExtra(EXT_RESERVATION_PEOPLE, numberOfPeople.text.toString())

                }
                startActivity(intent)
            }
        }
    }

    fun selectDate(onDateSelected: (LocalDate) -> Unit) {
        val calendar = Calendar.getInstance()
        val today = LocalDate.now()

        // 날짜 선택 다이얼로그
        DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val date = LocalDate.of(year, month + 1, dayOfMonth)
            if (date.isBefore(today)) {
                Toast.makeText(this, "예약 날짜는 오늘 날짜 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
            } else {
                onDateSelected(date) // 유효한 날짜 선택 후 콜백 실행
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }


}


//val editReservationDate = findViewById<EditText>(R.id.reservationDate)
//editReservationDate.setOnClickListener {
//    selectDateTime { selectedDateTime ->
//        editReservationDate.setText(selectedDateTime) // 선택한 날짜와 시간을 EditText에 설정
//    }
//}

//fun selectDateTime(onDateTimeSelected: (String) -> Unit) {
//        val calendar = Calendar.getInstance()
//        val today = LocalDate.now()
//
//        // 날짜 선택
//        DatePickerDialog(this, { _, year, month, dayOfMonth ->
//            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
//
//            if (selectedDate.isBefore(today)) {
//                Toast.makeText(this, "예약 날짜는 오늘 날짜 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
//            } else {
//                // 유효한 날짜 선택 후 시간 선택
//                selectTime { selectedTime ->
//                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//                    val dateTime = selectedDate.atTime(selectedTime).format(formatter)
//                    onDateTimeSelected(dateTime) // 선택한 날짜와 시간을 콜백으로 전달
//                }
//            }
//        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
//    }
//
//    fun selectTime(onTimeSelected: (LocalTime) -> Unit) {
//        val calendar = Calendar.getInstance()
//        val now = LocalTime.now()
//        Log.d("test",now.toString())
//
//        TimePickerDialog(this, { _, hourOfDay, minute ->
//            val selectedTime = LocalTime.of(hourOfDay, minute)
//
//            if (selectedTime.isBefore(now)) {
//                Toast.makeText(this, "예약 시간은 현재 시간 이후여야 합니다.", Toast.LENGTH_SHORT).show()
//            } else {
//                onTimeSelected(selectedTime) // 유효한 시간 선택 후 콜백 실행
//            }
//        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show() // true for 24-hour format
//    }