package edu.skku.cs.pa1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class WordAdapter(val context: Context, val items: ArrayList<Word>) : BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_word, null)

        val textViews = listOf<TextView>(
            view.findViewById(R.id.wordText1),
            view.findViewById(R.id.wordText2),
            view.findViewById(R.id.wordText3),
            view.findViewById(R.id.wordText4),
            view.findViewById(R.id.wordText5)
        )

        val word: String = items[p0].word.uppercase()
        val result: MutableList<Int> = items[p0].result
        for (i in word.indices) {
            textViews[i].text = word[i].toString()
            if(result[i]==0){
                textViews[i].setBackgroundColor(Color.parseColor("#FF787C7E"))
                textViews[i].setTextColor(ContextCompat.getColor(context, R.color.white))
            }else if(result[i]==1){
                textViews[i].setBackgroundColor(Color.parseColor("#FFFFE46F"))
                textViews[i].setTextColor(ContextCompat.getColor(context, R.color.black))
            }else{
                textViews[i].setBackgroundColor(Color.parseColor("#FF99F691"))
                textViews[i].setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }

        return view;
    }


}
