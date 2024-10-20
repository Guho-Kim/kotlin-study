package edu.skku.cs.pa1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val wordList = ArrayList<String>()  // 단어를 저장할 리스트
    private lateinit var wordAdapter: WordAdapter  // 어댑터 정의

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // XML에서 뷰 요소 연결
        val editTextWord = findViewById<EditText>(R.id.editTextWordle)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val listView = findViewById<ListView>(R.id.wordList)

//        // 어댑터 설정
//        wordAdapter = WordAdapter(wordList, this)
//        listView.adapter = wordAdapter

        /*
            기본으로 제공 되는 ArrayAdapter 사용할 수도 있음.
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, wordList)
            listView.adapter = adapter

            하지만, 이해를 위해 custom adapter 사용 해봐야 함. ArrayAdapter도 사용 해보자.

         */


        // 버튼 클릭 시 단어 추가
        buttonSubmit.setOnClickListener {
            val word = editTextWord.text.toString()
            if (word.isNotEmpty()) {
                wordList.add(word)  // 리스트에 단어 추가
//                wordAdapter.notifyDataSetChanged()  // 어댑터 업데이트
                editTextWord.text.clear()  // 입력 필드 초기화
            }
        }
    }
}