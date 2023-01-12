package com.example.ticketing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ticketing.adapter.EventAdapter
import com.example.ticketing.databinding.ActivityEventsBinding
import com.example.ticketing.model.Event

class EventsActivity : AppCompatActivity() {
    lateinit var binding:ActivityEventsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_events)




    }
}