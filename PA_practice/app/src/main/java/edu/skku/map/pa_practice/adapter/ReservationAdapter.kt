package edu.skku.map.pa_practice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import edu.skku.map.pa_practice.R
import edu.skku.map.pa_practice.model.Reservation
import edu.skku.map.pa_practice.model.Restaurant
import edu.skku.map.pa_practice.utils.logoUtil


class ReservationAdapter(val data:ArrayList<Reservation>, val context: Context): BaseAdapter(){
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
        val restaurantName = generatedView.findViewById<TextView>(R.id.restaurant_name)
        val numberOfPeople = generatedView.findViewById<TextView>(R.id.number_of_people)
        val reservationTime = generatedView.findViewById<TextView>(R.id.reservation_time)
        val reservationDate = generatedView.findViewById<TextView>(R.id.reservation_date)
        val logo = generatedView.findViewById<ImageView>(R.id.logo)

        restaurantName.text = data[p0].restaurantName
        numberOfPeople.text = data[p0].numberOfPeople.toString()
        reservationTime.text = data[p0].reservationTime
        val reservationDateTmp = data[p0].reservationDate
        val (year, month, day) = reservationDateTmp.split("-")

        reservationDate.text = "$day/$month/$year"
        val logoResource = logoUtil.getLogoResource(data[p0].restaurantName)
        logo.setImageResource(logoResource)

        return generatedView
    }
}