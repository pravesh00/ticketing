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
import com.example.ticketing.room.TicketingDatabase
import com.example.ticketing.room.TicketingRepository
import com.example.ticketing.viewmodel.OrderViewModel

class OrdersActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrdersBinding
    private lateinit var ticketingDatabase: TicketingDatabase
    private lateinit var repo: TicketingRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_orders)
        ticketingDatabase= TicketingDatabase(this)
        repo= TicketingRepository(TicketingDatabase(applicationContext))

        var orderViewModel = OrderViewModel(repo)
        val orders = ArrayList<OrderDetails>()
        orderViewModel.updateOrderData()


        var adapter = OrderAdapter(orders,orderViewModel)
        binding.recyclerOrders.adapter = adapter
        binding.recyclerOrders.layoutManager = LinearLayoutManager(this)

        this.setSupportActionBar(binding.toolbarTop)
        binding.toolbarTop.subtitle = "Your Orders"

        orderViewModel.list.observe(this){
            orders.clear()
            orders.addAll(it)
            adapter.notifyDataSetChanged()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.order_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.Filter -> {
            Toast.makeText(this,"Filter selected", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}