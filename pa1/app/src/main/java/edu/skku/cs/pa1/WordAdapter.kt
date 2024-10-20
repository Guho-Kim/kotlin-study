package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class WordAdapter {
    class WordAdapter(private val words: ArrayList<String>, private val context: Context) : BaseAdapter() {

        override fun getCount(): Int {
            return words.size
        }

        override fun getItem(position: Int): Any {
            return words[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val holder: ViewHolder

            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
                holder = ViewHolder()
                holder.textView = view.findViewById(android.R.id.text1)
                view.tag = holder
            } else {
                view = convertView
                holder = view.tag as ViewHolder
            }

            holder.textView?.text = words[position]
            return view
        }

        private class ViewHolder {
            var textView: TextView? = null
        }
}}