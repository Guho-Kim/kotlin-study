package edu.skku.map.pa_practice

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_ID
import edu.skku.map.pa_practice.adapter.RestaurantsAdapter
import edu.skku.map.pa_practice.model.Restaurant
import edu.skku.map.pa_practice.model.User

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val restaurantsListView = findViewById<ListView>(R.id.list_view_restaurants)
        val restaurantsItems = ArrayList<Restaurant>()

        val restaurantsAdapter = RestaurantsAdapter(restaurantsItems, applicationContext)
        restaurantsListView.adapter = restaurantsAdapter

        val restaurants = readRestaurantsFromJson()

        if (restaurants.isNotEmpty()) {
            restaurantsItems.addAll(restaurants)
            restaurantsAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
        }

        restaurantsListView.setOnItemClickListener { _, _, position, _ ->
            val selectedRestaurant = restaurantsItems[position]
//            saveSelectedRestaurant(selectedRestaurant)

            val intent = Intent(this@Activity2, Activity3::class.java).apply {
                putExtra(EXT_RESTAURANT_ID, selectedRestaurant.restaurant_id)
            }
            startActivity(intent)
            Toast.makeText(this, "Selected: ${selectedRestaurant.restaurant_id}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readRestaurantsFromJson(): List<Restaurant> {
        val assetManager = assets
        return try {
            val inputStream = assetManager.open("restaurant_info.json") // assets 디렉토리에서 파일 열기
            val json = inputStream.bufferedReader().use { it.readText() } // 파일 내용을 문자열로 읽기
            val type = object : TypeToken<List<Restaurant>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 선택된 레스토랑 저장
    private fun saveSelectedRestaurant(restaurant: Restaurant) {
        val sharedPreferences = getSharedPreferences("SelectedRestaurant", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val restaurantJson = Gson().toJson(restaurant)
        editor.putString("restaurant", restaurantJson)
        editor.apply()
    }
}