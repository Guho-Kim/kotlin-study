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
        letterView.text = items[p0].toString().uppercase()

        if(color == "green"){
            letterView.setBackgroundColor(Color.parseColor("#FF99F691"))
            letterView.setTextColor(ContextCompat.getColor(context, R.color.black))

        }else if(color == "yellow"){
            letterView.setBackgroundColor(Color.parseColor("#FFFFE46F"))
            letterView.setTextColor(ContextCompat.getColor(context, R.color.black))
        }else if(color == "gray"){
            letterView.setBackgroundColor(Color.parseColor("#FF787C7E"))
            letterView.setTextColor(ContextCompat.getColor(context, R.color.white))
        }


        return view;
    }


}