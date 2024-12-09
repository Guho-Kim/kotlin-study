package edu.skku.map.pa_practice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.map.pa_practice.model.User

class Activity0 : AppCompatActivity() {
    companion object{
        const val EXT_USER_ID = "extra_key_user_id"
        const val EXT_USER_NAME = "extra_key_user_name"
        const val EXT_USER_AGE = "extra_key_user_age"
        const val EXT_USER_GENDER = "extra_key_user_gender"
        const val EXT_RESTAURANT_ID = "extra_key_restaurant_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_0)

        val idInput = findViewById<EditText>(R.id.input_id)
        val passwordInput = findViewById<EditText>(R.id.input_password)
        val loginButton = findViewById<Button>(R.id.login_btn)

        loginButton.setOnClickListener {
            val userId = idInput.text.toString()
            val userPassword = passwordInput.text.toString()

            if (userId.isBlank() || userPassword.isBlank()) {
                Toast.makeText(this, "아이디와 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val users = readUsersFromJson()

            val user = users.find { it.id == userId }
            if (user == null) {
                Toast.makeText(this, "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else if (user.password != userPassword) {
                Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()



                val sharedPref = getSharedPreferences("LoginPrefs", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("userId", user.id)
                editor.apply()

                // 로그인 성공 시, 메인 화면으로 이동하거나 필요한 작업 수행
                val intent = Intent(this@Activity0, Activity1::class.java).apply {
                    putExtra(EXT_USER_ID, user.id)
                    putExtra(EXT_USER_AGE, user.age)
                    putExtra(EXT_USER_GENDER, user.gender)
                    putExtra(EXT_USER_NAME, user.name)
                }
                startActivity(intent)
//                finish()
            }
        }
    }


    // app/src/main/assets
    private fun readUsersFromJson(): List<User> {
        val assetManager = assets
        return try {
            val inputStream = assetManager.open("user_info.json") // assets 디렉토리에서 파일 열기
            val json = inputStream.bufferedReader().use { it.readText() } // 파일 내용을 문자열로 읽기
            val type = object : TypeToken<List<User>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            Toast.makeText(this, "사용자 정보 파일을 읽는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }

}