package edu.skku.map.week4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var linearLayout = findViewById<LinearLayout>(R.id.subLayout)
        var button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val layoutInflater: LayoutInflater=
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            layoutInflater.inflate(R.layout.sub_layout, linearLayout, true)

            var img1 = findViewById<ImageView>(R.id.imageView)
            var img2 = findViewById<ImageView>(R.id.imageView2)

            img1.setImageResource(R.drawable.bbq)
            img2.setImageResource(R.drawable.bhc)
        }

        var buttonPizza = findViewById<Button>(R.id.buttonPizza)
        var buttonHamburger = findViewById<Button>(R.id.buttonHamburger)
        var buttonChicken = findViewById<Button>(R.id.buttonChicken)

        var mainList = findViewById<ListView>(R.id.listView)


        val pizzaItems = arrayListOf<Restaurant>()
        val hamburgerItems = arrayListOf<Restaurant>()
        val chickenItems = arrayListOf<Restaurant>()

        pizzaItems.add(Restaurant(R.drawable.domino, "domino pizza"))
        pizzaItems.add(Restaurant(R.drawable.pizzahut, "pizza hut"))
        pizzaItems.add(Restaurant(R.drawable.pizzanarachickengongju, "pizza nara chicken gongju"))
        val pizzaListAdapter = CustomAdapter(this, pizzaItems)

        hamburgerItems.add(Restaurant(R.drawable.burgerking, "burgerking"))
        hamburgerItems.add(Restaurant(R.drawable.mcdonalds, "mcdonalds"))
        hamburgerItems.add(Restaurant(R.drawable.momstouch, "momstouch"))
        hamburgerItems.add(Restaurant(R.drawable.lotteria, "lotteria"))
        val hamburgerListAdapter = CustomAdapter(this, hamburgerItems)

        chickenItems.add(Restaurant(R.drawable.bbq, "BBQ"))
        chickenItems.add(Restaurant(R.drawable.bhc, "BHC"))
        chickenItems.add(Restaurant(R.drawable.goobne, "goobne"))
        chickenItems.add(Restaurant(R.drawable.pizzanarachickengongju, "pizza nara chicken gongju"))
        val chickenListAdapter = CustomAdapter(this, chickenItems)

        // Default list is Pizza
        mainList.adapter = pizzaListAdapter

        buttonPizza.setOnClickListener {
            mainList.adapter = pizzaListAdapter
        }
        buttonHamburger.setOnClickListener {
            mainList.adapter = hamburgerListAdapter
        }
        buttonChicken.setOnClickListener{
            mainList.adapter = chickenListAdapter
        }
    }
}