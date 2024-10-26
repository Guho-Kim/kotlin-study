package edu.skku.cs.pa1

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random


/*
    1. 제출 전에 Result clear
    2. 디자인 값 체크

 */

class MainActivity : AppCompatActivity() {
    companion object {
        const val COUNT_KEY = "count_key"
        const val WORD_KEY = "word_key"
    }

    private var ans: String = ""
    private val validWords = mutableSetOf<String>()
    private var cnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 제출 전에 1회 실행 후 주석
//        clearAllResult(this)

        // minimum number of attempts 출력
        val lastCount = getLastCount(this)
        val lastWord = getLastWord(this)
        if(lastCount<0){
            Toast.makeText(this, "This is the first attempt.", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "The MIN attempts : ${lastWord} - ${lastCount}", Toast.LENGTH_SHORT).show()
        }

        // 단어 리스트 load
        loadWords()
        setAnswer()
        // Debug
        Log.d(localClassName, "ans: ${ans}")

        // View 연결
        val editTextWord = findViewById<EditText>(R.id.editTextWordle)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val wordList = findViewById<ListView>(R.id.wordList)
        val grayLetterList = findViewById<ListView>(R.id.grayLetterList)
        val yellowLetterList = findViewById<ListView>(R.id.yellowLetterList)
        val greenLetterList = findViewById<ListView>(R.id.greenLetterList)

        // Items List 생성
        val wordItems = ArrayList<Word>()
        val grayItems = ArrayList<Char>()
        val yellowItems = ArrayList<Char>()
        val greenItems = ArrayList<Char>()

        // Adapter 생성 및 연결
        val wordAdapter = WordAdapter(this, wordItems)
        wordList.adapter = wordAdapter
        val grayLetterAdapter = LetterAdapter(this, grayItems, "gray")
        grayLetterList.adapter = grayLetterAdapter
        val yellowLetterAdapter = LetterAdapter(this, yellowItems, "yellow")
        yellowLetterList.adapter = yellowLetterAdapter
        val greenLetterAdapter = LetterAdapter(this, greenItems, "green")
        greenLetterList.adapter = greenLetterAdapter

        buttonSubmit.setOnClickListener {
            // 입력 필드 저장
            val word = editTextWord.text.toString().lowercase()

            if (word.isNotEmpty()) {
                /*
                    0 = letter is not included
                    1 = right letter but wrong position
                    2 = right letter on right position
                 */
                val result = MutableList(word.length) { 0 }

                if (validWords.contains(word)) {
                    // 입력 횟수 카운트
                    cnt++

                    // correct letter
                    for (i in word.indices) {
                        if (word[i] == ans[i]) {
                            result[i] = 2
                        }
                    }

                    // present letter
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
                        if(lastCount==-1 || lastCount > cnt){
                            saveResult(this, cnt, word)
                        }
                        // Debug
                        Log.d(localClassName, "answer: ${word} count: ${cnt}")
                    }

                    // Update word items
                    wordItems.add(Word(word, result))

                    // Update letter items
                    for (i in word.indices){
                        val letter = word[i]
                        if(result[i]==0){
                            if(!grayItems.contains(letter) && !yellowItems.contains(letter) && !greenItems.contains(letter)){
                                grayItems.add(letter)
                            }
                        }else if(result[i]==1){
                            if(grayItems.contains(letter)){
                                grayItems.remove(letter)
                            }
                            if(!yellowItems.contains(letter) && !greenItems.contains(letter)){
                                yellowItems.add(letter)
                            }
                        }else if(result[i]==2){
                            if(grayItems.contains(letter)){
                                grayItems.remove(letter)
                            }
                            if(yellowItems.contains(letter)){
                                yellowItems.remove(letter)
                            }
                            if(!greenItems.contains(letter)){
                                greenItems.add(letter)
                            }
                        }
                    }
                    // 입력 필드 초기화
                    editTextWord.text.clear()

                    // Update adapter
                    wordAdapter.notifyDataSetChanged()
                    grayLetterAdapter.notifyDataSetChanged()
                    yellowLetterAdapter.notifyDataSetChanged()
                    greenLetterAdapter.notifyDataSetChanged()
                } else {
                    // 단어가 없는 경우
                    Toast.makeText(this, "Word '${word}' not in dictionary!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadWords() {
        try {
            val inputStream = applicationContext.assets.open("wordle_words.txt")
            val content = inputStream.readBytes().toString(Charsets.UTF_8)
            validWords.addAll(content.lines())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setAnswer(){
        val validWordsList = validWords.toList()
        ans = validWordsList[Random.nextInt(validWordsList.size)]
    }

    private fun saveResult(context: Context, intValue: Int, stringValue: String) {
        val sharedPreferences = context.getSharedPreferences("WordleProject", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt(COUNT_KEY, intValue)
            putString(WORD_KEY, stringValue)
            apply()
        }
    }

    private fun getLastCount(context: Context, defaultValue: Int = -1): Int {
        val sharedPreferences = context.getSharedPreferences("WordleProject", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(COUNT_KEY, defaultValue)
    }

    private fun getLastWord(context: Context, defaultValue: String = ""): String? {
        val sharedPreferences = context.getSharedPreferences("WordleProject", Context.MODE_PRIVATE)
        return sharedPreferences.getString(WORD_KEY, defaultValue)
    }

    private fun clearAllResult(context: Context) {
        val sharedPreferences = context.getSharedPreferences("WordleProject", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}