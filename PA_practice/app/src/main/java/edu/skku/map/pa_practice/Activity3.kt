package edu.skku.map.pa_practice

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_ID
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_NAME
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_OPENING_HOURS
import edu.skku.map.pa_practice.adapter.MenuAdapter
import edu.skku.map.pa_practice.model.Menu
import edu.skku.map.pa_practice.model.Restaurant
import edu.skku.map.pa_practice.utils.logoUtil

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val restaurantName = findViewById<TextView>(R.id.restaurant_name)
        val restaurantRating = findViewById<TextView>(R.id.restaurant_rating)
        val restaurantLocation = findViewById<TextView>(R.id.restaurant_location)
        val restaurantDescription = findViewById<TextView>(R.id.restaurant_description)
        val restaurantOpeningHours = findViewById<TextView>(R.id.restaurant_opening_hours)
        val reservationBtn = findViewById<TextView>(R.id.reservation_btn)
        val restaurantLogo = findViewById<ImageView>(R.id.restaurant_logo)
        val menusListView = findViewById<ListView>(R.id.list_view_menus)
        val menuItems = ArrayList<Menu>()
        val menuAdapter = MenuAdapter(menuItems, applicationContext)
        menusListView.adapter = menuAdapter


        // 전달받은 restaurantId 확인
        val restaurantId = intent.getStringExtra(EXT_RESTAURANT_ID)
        if (restaurantId == null) {
            Toast.makeText(this, "Restaurant ID is missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // JSON 데이터 읽기 및 필터링
        val restaurants = readRestaurantsFromJson()
        val selectedRestaurant = restaurants.find { it.restaurant_id == restaurantId }




        if (selectedRestaurant != null) {
            restaurantName.text = selectedRestaurant.restaurant_name
            restaurantDescription.text = selectedRestaurant.description
            restaurantLocation.text = selectedRestaurant.location
            restaurantOpeningHours.text = selectedRestaurant.open_time+" ~ "+selectedRestaurant.close_time
            restaurantRating.text = selectedRestaurant.rating.toString()
            val logoResource = logoUtil.getLogoResource(selectedRestaurant.restaurant_name)
            restaurantLogo.setImageResource(logoResource)

            // 메뉴 데이터 추가 및 어댑터 업데이트
            menuItems.addAll(selectedRestaurant.menu)
            menuAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Restaurant not found for ID: $restaurantId", Toast.LENGTH_SHORT).show()
        }


        reservationBtn.setOnClickListener {
            val intent = Intent(this@Activity3, Activity4::class.java).apply {
                putExtra(EXT_RESTAURANT_ID, restaurantId)
                putExtra(EXT_RESTAURANT_NAME, restaurantName.text.toString())
                putExtra(EXT_RESTAURANT_OPENING_HOURS, restaurantOpeningHours.text.toString())
            }
            startActivity(intent)
        }

    }

    private fun readRestaurantsFromJson(): List<Restaurant> {
        val assetManager = assets
        return try {
            val inputStream = assetManager.open("restaurant_info.json")
            val json = inputStream.bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Restaurant>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
