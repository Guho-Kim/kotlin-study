package edu.skku.map.pa_practice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import edu.skku.map.pa_practice.R
import edu.skku.map.pa_practice.model.Restaurant


class MyReservationsAdapter(val data:ArrayList<Restaurant>, val context: Context): BaseAdapter(){
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
        val generatedView = inflater.inflate(R.layout.item_my_reservations, null)

//        val restaurantName = generatedView.findViewById<TextView>(R.id.restaurant_name)
//        val locationRating = generatedView.findViewById<TextView>(R.id.location_rating)
//        val openCloseTime = generatedView.findViewById<TextView>(R.id.open_close_time)
//        val logo = generatedView.findViewById<ImageView>(R.id.logo)
//
//        restaurantName.text = data[p0].restaurant_name
//        locationRating.text = data[p0].location + " / " + data[p0].rating
//        openCloseTime.text = data[p0].open_time + " ~ " + data[p0].close_time
//
//        logo.setImageResource(R.drawable.default_air_logo)

        return generatedView
    }
}