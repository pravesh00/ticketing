package com.example.ticketing

import android.os.Bundle
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        Glide.with(this).load(event.photo).centerCrop().into(binding.imgPhoto)
        binding.txtDesc.setText(event.description)
        binding.txtName.setText(event.name)
        binding.txtType.setText(event.typeEvent)
        var linearLayout = binding.lnrTable
        var m = Array(10) {Array(15) {0} }
        var totalSeats:Int = 0

        for(i in 0..9){
            var newlinear = LinearLayout(this)

            for( j in 0..14){
                var chk = CheckBox(this)
                chk.setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1f
                    )
                )
                chk.setOnClickListener {
                    if(m[i][j]===2){
                        m[i][j]=0
                        totalSeats-=1
                    }else {
                        m[i][j]=2
                        totalSeats+=1
                    }
                    binding.txtTickets.text = "Tickets : "+totalSeats.toString()
                }
                if(m[i][j]===1){
                    chk.isChecked= true
                    chk.isEnabled=false
                }
                newlinear.addView(chk)
            }
            linearLayout.addView(newlinear)
        }


    }
}