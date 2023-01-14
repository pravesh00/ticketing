package com.example.ticketing

import android.app.Dialog
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
import com.example.ticketing.databinding.FilterLayoutEventBinding
import com.example.ticketing.databinding.FilterOrderLayoutBinding
import com.example.ticketing.model.OrderDetails
import com.example.ticketing.room.TicketingDatabase
import com.example.ticketing.room.TicketingRepository
import com.example.ticketing.viewmodel.OrderViewModel

class OrdersActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrdersBinding
    private lateinit var ticketingDatabase: TicketingDatabase
    private lateinit var repo: TicketingRepository
    private lateinit var dialogBox: Dialog
    lateinit var dialogBinding: FilterOrderLayoutBinding
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

        dialogBox= Dialog(this)

        dialogBinding= FilterOrderLayoutBinding.inflate(layoutInflater)
        dialogBox.setContentView(dialogBinding.root)
        dialogBox.setTitle("Filter Options")
        dialogBox.setCancelable(true)

        orderViewModel.list.observe(this){
            orders.clear()
            orders.addAll(it)
            adapter.notifyDataSetChanged()
        }

        dialogBinding.btnFilter.setOnClickListener {
            var status = "Confirmed"
            if(dialogBinding.radioInclude.isChecked){
                if (dialogBinding.chkComedy.isChecked && dialogBinding.chkMovie.isChecked && dialogBinding.chkPlay.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed")
                } else if (dialogBinding.chkComedy.isChecked && dialogBinding.chkMovie.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed","Comedy Show", "Movie")
                } else if (dialogBinding.chkComedy.isChecked && dialogBinding.chkPlay.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed","Comedy Show", "Play")
                } else if (dialogBinding.chkPlay.isChecked && dialogBinding.chkMovie.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed","Movie", "Play")
                } else if (dialogBinding.chkComedy.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed","Comedy Show")

                } else if (dialogBinding.chkPlay.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed","Play")
                } else if (dialogBinding.chkMovie.isChecked) {
                    orderViewModel.updateOrderDataStatus("Confirmed","Movie")
                } else {
                    orderViewModel.updateOrderDataStatus("Confirmed")
                }
            }else {
                if (dialogBinding.chkComedy.isChecked && dialogBinding.chkMovie.isChecked && dialogBinding.chkPlay.isChecked) {
                    orderViewModel.updateOrderData()
                } else if (dialogBinding.chkComedy.isChecked && dialogBinding.chkMovie.isChecked) {
                    orderViewModel.updateOrderData("Comedy Show", "Movie")
                } else if (dialogBinding.chkComedy.isChecked && dialogBinding.chkPlay.isChecked) {
                    orderViewModel.updateOrderData("Comedy Show", "Play")
                } else if (dialogBinding.chkPlay.isChecked && dialogBinding.chkMovie.isChecked) {
                    orderViewModel.updateOrderData("Movie", "Play")
                } else if (dialogBinding.chkComedy.isChecked) {
                    orderViewModel.updateOrderData("Comedy Show")

                } else if (dialogBinding.chkPlay.isChecked) {
                    orderViewModel.updateOrderData("Play")
                } else if (dialogBinding.chkMovie.isChecked) {
                    orderViewModel.updateOrderData("Movie")
                } else {
                    orderViewModel.updateOrderData()
                }
            }
            dialogBox.cancel()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.order_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.Filter -> {
            dialogBox.show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}