package edu.skku.cs.pa1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class LetterAdapter(val context: Context, val items: ArrayList<Char>, val color: String) :BaseAdapter() {
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
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_letter, null)
        val letterView = view.findViewById<TextView>(R.id.letterView)

        val letter = items[p0].toString().uppercase()
        if (letter.isNotEmpty()) {
            letterView.text = letter
            if(color == "gray"){
                letterView.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
                letterView.setTextColor(ContextCompat.getColor(context, R.color.white))
            }else if(color == "yellow"){
                letterView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                letterView.setTextColor(ContextCompat.getColor(context, R.color.black))
            }else if(color == "green"){
                letterView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
                letterView.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }

        return view;
    }
}