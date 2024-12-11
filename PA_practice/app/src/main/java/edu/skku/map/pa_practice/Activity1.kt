package edu.skku.map.pa_practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_DATE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_PEOPLE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESERVATION_TIME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_ID
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_NAME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_AGE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_GENDER
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_ID
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_NAME
import edu.skku.map.pa_practice.adapter.ReservationAdapter
import edu.skku.map.pa_practice.adapter.RestaurantAdapter
import edu.skku.map.pa_practice.model.Reservation

class Activity1 : AppCompatActivity() {
    private lateinit var reservationItems: ArrayList<Reservation> // 클래스 변수로 선언
    private lateinit var reservationAdapter: ReservationAdapter   // 클래스 변수로 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val userInfo = findViewById<TextView>(R.id.user_info)
        val user_id = intent.getStringExtra(EXT_USER_ID)
        val user_name = intent.getStringExtra(EXT_USER_NAME)
        val user_age = intent.getIntExtra(EXT_USER_AGE, 0)
        val user_gender = intent.getStringExtra(EXT_USER_GENDER)
        userInfo.text = "$user_id: $user_name($user_age/$user_gender)"

        reservationItems = ArrayList()
        reservationAdapter = ReservationAdapter(reservationItems, applicationContext)

        val reservationListView = findViewById<ListView>(R.id.reservation_list_view)

        reservationListView.adapter = reservationAdapter
        updateItem()

        val reservationBtn = findViewById<Button>(R.id.reservation_btn)

        reservationBtn.setOnClickListener {
            val intent = Intent(this@Activity1, Activity2::class.java)
            startActivity(intent)
        }

        reservationListView.setOnItemClickListener { _, _, position, _ ->
            val selectedReservation = reservationItems[position]

            val intent = Intent(this@Activity1, Activity6::class.java).apply {
                putExtra(EXT_RESERVATION_PEOPLE, selectedReservation.numberOfPeople.toString())
                putExtra(EXT_RESERVATION_TIME, selectedReservation.reservationTime)
                putExtra(EXT_RESERVATION_DATE, selectedReservation.reservationDate)
                putExtra(EXT_RESTAURANT_NAME, selectedReservation.restaurantName)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateItem()
    }

    fun updateItem(){
        val loginPrefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
        val user_id = loginPrefs.getString("userId", null).toString()


        val sharedPref = getSharedPreferences("MyReservationPrefs_$user_id", MODE_PRIVATE)
        val gson = Gson()


        // 저장된 JSON 문자열 가져오기
        val json = sharedPref.getString("reservations", null)
        val type = object : TypeToken<MutableList<Reservation>>() {}.type

        // JSON 문자열 -> 리스트 변환
        val reservationList: MutableList<Reservation> = gson.fromJson(json, type) ?: mutableListOf()


        reservationItems.clear() // 기존 데이터 초기화
        reservationItems.addAll(reservationList) // 가져온 데이터를 추가
        reservationAdapter.notifyDataSetChanged()
    }
}


//        val sharedPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
//        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
//        val user_id = sharedPref.getString("userId", null)
//        if(!isLoggedIn){
//            val intent = Intent(this, Activity0::class.java)
//            startActivity(intent)
//        }