package edu.skku.map.pa_practice

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.skku.map.pa_practice.Activity0.Companion.EXT_RESTAURANT_ID
import edu.skku.map.pa_practice.adapter.MenusAdapter
import edu.skku.map.pa_practice.model.Menu
import edu.skku.map.pa_practice.model.Restaurant

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val menusListView = findViewById<ListView>(R.id.list_view_menus)
        val menuItems = ArrayList<Menu>()
        val menusAdapter = MenusAdapter(menuItems, applicationContext)
        menusListView.adapter = menusAdapter

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
            // 메뉴 데이터 추가 및 어댑터 업데이트
            menuItems.addAll(selectedRestaurant.menu)
            menusAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Restaurant not found for ID: $restaurantId", Toast.LENGTH_SHORT).show()
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
