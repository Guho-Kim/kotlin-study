package edu.skku.map.pa_practice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import edu.skku.map.pa_practice.R
import edu.skku.map.pa_practice.model.Menu

class MenuAdapter(val data:ArrayList<Menu>, val context: Context): BaseAdapter(){
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.item_menus, null)

        val menuName = generatedView.findViewById<TextView>(R.id.menu_name)
        val price = generatedView.findViewById<TextView>(R.id.price)
        val logo = generatedView.findViewById<ImageView>(R.id.logo)

        menuName.text = data[p0].name
        price.text = data[p0].price.toString()
        logo.setImageResource(R.drawable.default_air_logo)

        return generatedView
    }
}