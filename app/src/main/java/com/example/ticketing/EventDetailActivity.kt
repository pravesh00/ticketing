package com.example.ticketing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.ticketing.databinding.ActivityEventDetailBinding
import com.example.ticketing.model.Event

class EventDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityEventDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        var event = intent.getSerializableExtra("event") as Event
        //Toast.makeText(this,event.name.toString(),Toast.LENGTH_SHORT).show()
        Glide.with(this).load(event.photo).centerCrop().into(binding.imgPhoto)
        binding.txtDesc.setText(event.description)
        binding.txtName.setText(event.name)
        binding.txtType.setText(event.typeEvent)

    }
}