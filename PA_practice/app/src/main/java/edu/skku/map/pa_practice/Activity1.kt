package edu.skku.map.pa_practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_AGE
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_GENDER
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_ID
import edu.skku.map.pa_practice.Activity0.Companion.EXT_USER_NAME

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val userInfo = findViewById<TextView>(R.id.user_info)
//        val sharedPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
//        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
//        val user_id = sharedPref.getString("userId", null)
//        if(!isLoggedIn){
//            val intent = Intent(this, Activity0::class.java)
//            startActivity(intent)
//        }
        val user_id = intent.getStringExtra(EXT_USER_ID)
        val user_name = intent.getStringExtra(EXT_USER_NAME)
        val user_age = intent.getIntExtra(EXT_USER_AGE, 0)
        val user_gender = intent.getStringExtra(EXT_USER_GENDER)
        userInfo.text = "$user_id: $user_name($user_age/$user_gender)"

        val reservationBtn = findViewById<Button>(R.id.reservation_btn)

        reservationBtn.setOnClickListener {
            val intent = Intent(this@Activity1, Activity2::class.java)
            startActivity(intent)
        }
    }

}
