package edu.skku.map.pa_practice

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class LoginActivity : AppCompatActivity() {
    data class User(val id: String, val password: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val idInput = findViewById<EditText>(R.id.input_id)
        val passwordInput = findViewById<EditText>(R.id.input_password)
        val loginButton = findViewById<Button>(R.id.button_login)

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

                // 로그인 성공 시, 메인 화면으로 이동하거나 필요한 작업 수행
                finish()
            }
        }
    }

    private fun readUsersFromJson(): List<User> {
        val file = File(filesDir, "user_info.json")
        if (!file.exists()) {
            Toast.makeText(this, "사용자 정보 파일이 없습니다.", Toast.LENGTH_SHORT).show()
            return emptyList()
        }

        val json = file.readText()
        val type = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(json, type)
    }
}
