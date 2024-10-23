package edu.skku.cs.pa1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var ans: String = ""
    private val validWords = mutableSetOf<String>()
    private var cnt: Int = 0
/*
    대소문자 이슈 해결


 */

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 중복 함수 이름 조심
        loadWordsFromAssets()
        // 나중에 주석 풀기
        val validWordsList = validWords.toList()
        ans = validWordsList[Random.nextInt(validWordsList.size)]
//        ans="lousy"

        // XML에서 뷰 요소 연결
        val editTextWord = findViewById<EditText>(R.id.editTextWordle)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val wordList = findViewById<ListView>(R.id.wordList)
        val grayLetterList = findViewById<ListView>(R.id.grayLetterList)
        val yellowLetterList = findViewById<ListView>(R.id.yellowLetterList)
        val greenLetterList = findViewById<ListView>(R.id.greenLetterList)



        val grayItems = ArrayList<Char>()
        val yellowItems = ArrayList<Char>()
        val greenItems = ArrayList<Char>()
        val grayLetterAdapter = LetterAdapter(this, grayItems, "gray")
        val yellowLetterAdapter = LetterAdapter(this, yellowItems, "yellow")
        val greenLetterAdapter = LetterAdapter(this, greenItems, "green")
        grayLetterList.adapter = grayLetterAdapter
        yellowLetterList.adapter = yellowLetterAdapter
        greenLetterList.adapter = greenLetterAdapter


        val wordItems = ArrayList<Word>()
        val wordAdapter = WordAdapter(this, wordItems)
        wordList.adapter = wordAdapter



        buttonSubmit.setOnClickListener {
            val word = editTextWord.text.toString().trim() // 사용자가 입력한 단어
            // 결과를 숫자로 저장. 0 = absent, 1 = present, 2 = correct
            val result = MutableList(word.length) { 0 }
            if (word.isNotEmpty()) {
                if (validWords.contains(word)) {
                    cnt++
                    // 1단계: 위치가 정확한 문자 ("correct")
                    for (i in word.indices) {
                        if (word[i] == ans[i]) {
                            result[i] = 2
                        }
                    }

                    for (i in word.indices) {
                        if (result[i] == 2) continue
                        for (j in ans.indices) {
                            if (word[i] == ans[j]) {
                                result[i] = 1
                            }
                        }
                    }

                    // 정답인 경우 cnt 로컬에 저장
                    if(word==ans){
//                        cnt 저장
//                        test용 toast
                        Toast.makeText(this, "Word ${word} is answer! You tried ${cnt} times!", Toast.LENGTH_SHORT).show()
                    }

                    // letterList
                    for (i in word.indices){
                        val letter = word[i]
                        if(result[i]==0){
                            if(!grayItems.contains(letter) && !yellowItems.contains(letter) && !greenItems.contains(letter)){
                                grayItems.add(letter)
                            }
                        }else if(result[i]==1){
                            if(!yellowItems.contains(letter) && !greenItems.contains(letter)){
                                yellowItems.add(letter)
                            }
                            if(grayItems.contains(letter)){
                                grayItems.remove(letter)
                            }
                        }else if(result[i]==2){
                            if(!greenItems.contains(letter)){
                                greenItems.add(letter)
                            }
                            if(grayItems.contains(letter)){
                                grayItems.remove(letter)
                            }
                            if(yellowItems.contains(letter)){
                                yellowItems.remove(letter)
                            }
                        }
                    }



                    wordItems.add(Word(word, result))  // 리스트에 단어 추가
                    editTextWord.text.clear()  // 입력 필드 초기화
                    wordAdapter.notifyDataSetChanged()  // 어댑터 업데이트
                    grayLetterAdapter.notifyDataSetChanged()
                    yellowLetterAdapter.notifyDataSetChanged()
                    greenLetterAdapter.notifyDataSetChanged()

                } else {
                    // 단어가 없을 경우 사용자에게 알림 표시
                    Toast.makeText(this, "Word ${word} not in dictionary!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun loadWordsFromAssets() {
        try {
            val inputStream = assets.open("wordle_words.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            // 파일을 한 줄씩 읽어서 validWords에 추가
            while (reader.readLine().also { line = it } != null) {
                validWords.add(line!!.trim()) // 단어를 Set에 추가
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}