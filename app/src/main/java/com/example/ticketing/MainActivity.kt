package com.example.ticketing

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketing.adapter.EventAdapter
import com.example.ticketing.databinding.ActivityMainBinding
import com.example.ticketing.databinding.FilterLayoutEventBinding
import com.example.ticketing.model.Event
import com.example.ticketing.room.TicketingDatabase
import com.example.ticketing.room.TicketingRepository
import com.example.ticketing.viewmodel.EventViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private lateinit var ticketingDatabase: TicketingDatabase
    private lateinit var repo: TicketingRepository
    private lateinit var dialogBox:Dialog
    lateinit var dialogBinding:FilterLayoutEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        ticketingDatabase= TicketingDatabase(this)
        repo= TicketingRepository(TicketingDatabase(applicationContext))
        var list = ArrayList<Event>()

        dialogBox= Dialog(this)

        dialogBinding= FilterLayoutEventBinding.inflate(layoutInflater)
        dialogBox.setContentView(dialogBinding.root)
        dialogBox.setTitle("Filter Options")
        dialogBox.setCancelable(true)


        var adapter = EventAdapter(list)
        binding.recyclerEvents.adapter=adapter
        binding.recyclerEvents.layoutManager = LinearLayoutManager(this)

        var eventView = EventViewModel(repo)
        eventView.list.observe(this) {
            list.clear()
            list.addAll(it)
            if(it.size==0){
                eventView.prePopulateEventData()
            }
            adapter.notifyDataSetChanged()
            binding.refresh.isRefreshing=false
        }

        eventView.updateEventData()

        dialogBinding.btnFilter.setOnClickListener {
            if(dialogBinding.chkComedy.isChecked && dialogBinding.chkMovie.isChecked && dialogBinding.chkPlay.isChecked){
                eventView.updateEventData()
            }else if(dialogBinding.chkComedy.isChecked && dialogBinding.chkMovie.isChecked){
                eventView.updateEventData("Comedy Show","Movie")
            }else if(dialogBinding.chkComedy.isChecked && dialogBinding.chkPlay.isChecked){
                eventView.updateEventData("Comedy Show","Play")
            }else if(dialogBinding.chkPlay.isChecked && dialogBinding.chkMovie.isChecked){
                eventView.updateEventData("Movie","Play")
            }else if(dialogBinding.chkComedy.isChecked){
                eventView.updateEventData("Comedy Show")

            }else if(dialogBinding.chkPlay.isChecked){
                eventView.updateEventData("Play")
            }else if(dialogBinding.chkMovie.isChecked){
                eventView.updateEventData("Movie")
            }else{
                eventView.updateEventData()
            }
            dialogBox.cancel()
        }

        this.setSupportActionBar(binding.toolbarTop)
        binding.toolbarTop.subtitle = "Today's Shows"

        binding.refresh.setOnRefreshListener {
            eventView.updateEventData()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomnavmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter -> {
            dialogBox.show()
            true
        }
        R.id.order ->{
            startActivity(Intent(this,OrdersActivity::class.java))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}