package com.example.ticketing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketing.adapter.EventAdapter
import com.example.ticketing.databinding.ActivityMainBinding
import com.example.ticketing.model.Event
import com.example.ticketing.room.TicketingDatabase
import com.example.ticketing.room.TicketingRepository
import com.example.ticketing.viewmodel.EventViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private lateinit var ticketingDatabase: TicketingDatabase
    private lateinit var repo: TicketingRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        ticketingDatabase= TicketingDatabase(this)
        repo= TicketingRepository(TicketingDatabase(applicationContext))
        var list = ArrayList<Event>()

        var adapter = EventAdapter(list)
        binding.recyclerEvents.adapter=adapter
        binding.recyclerEvents.layoutManager = LinearLayoutManager(this)

        var eventView = EventViewModel(repo)
        eventView.list.observe(this) {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        }
        //eventView.prePopulateEventData()
        eventView.updateEventData()


        fun filterList(){
//            list1.clear()
//            for( i in list){
//                if(i.typeEvent=="Comedy Show" && binding.chkComedy.isChecked){
//                    list1.add(i)
//                }
//                if(i.typeEvent=="Movie" && binding.chkMovie.isChecked){
//                    list1.add(i)
//                }
//                if(i.typeEvent=="Play" && binding.chkPlay.isChecked){
//                    list1.add(i)
//                }
//            }
//            adapter.notifyDataSetChanged()

        }
        binding.chkComedy.setOnClickListener{
            filterList()
        }
        binding.chkMovie.setOnClickListener {
            filterList()
        }
        binding.chkPlay.setOnClickListener {
            filterList()
        }

        this.setSupportActionBar(binding.toolbarTop)
        binding.toolbarTop.subtitle = "Today's Shows"


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomnavmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter -> {
            // User chose the "Print" item
            Toast.makeText(this,"Filter selected",Toast.LENGTH_LONG).show()
            true
        }
        R.id.order ->{
            startActivity(Intent(this,OrdersActivity::class.java))
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}