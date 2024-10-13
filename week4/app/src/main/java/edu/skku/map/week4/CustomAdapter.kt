package edu.skku.map.week4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Restaurant (val id: Int, val name: String)
class CustomAdapter (val context: Context, val items: ArrayList<Restaurant>): BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(postition: Int, converView: View?, parent: ViewGroup?): View{
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item, null)

        var imageView = view.findViewById<ImageView>(R.id.itemImageView)
        var textView = view.findViewById<TextView>(R.id.itemTextView)

        textView.text = items[postition].name
        imageView.setImageResource(items[postition].id)

        return view
    }

}