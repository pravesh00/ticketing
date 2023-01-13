package com.example.ticketing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketing.adapter.OrderAdapter
import com.example.ticketing.databinding.ActivityMainBinding
import com.example.ticketing.databinding.ActivityOrdersBinding
import com.example.ticketing.model.OrderDetails

class OrdersActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrdersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_orders)
        val orders = ArrayList<OrderDetails>()
        orders.add(OrderDetails("12","Booked","122","Rab ne bna di jodi","","Movie","https://i.imgur.com/CxvVAcK.jpg"))
        var adapter = OrderAdapter(orders)
        binding.recyclerOrders.adapter = adapter
        binding.recyclerOrders.layoutManager = LinearLayoutManager(this)

        this.setSupportActionBar(binding.toolbarTop)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomnavmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter -> {
            // User chose the "Print" item
            Toast.makeText(this,"Filter selected", Toast.LENGTH_LONG).show()
            true
        }
        R.id.order ->{
            Toast.makeText(this,"Order selected", Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}