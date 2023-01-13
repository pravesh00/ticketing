package com.example.ticketing

import android.R.string
import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.ticketing.databinding.ActivityEventDetailBinding
import com.example.ticketing.model.Event
import com.example.ticketing.room.TicketingDatabase
import com.example.ticketing.room.TicketingRepository
import com.example.ticketing.viewmodel.EventDetailsViewModel


class EventDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityEventDetailBinding
    private lateinit var ticketingDatabase: TicketingDatabase
    private lateinit var repo: TicketingRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_event_detail)

        ticketingDatabase= TicketingDatabase(this)
        repo= TicketingRepository(TicketingDatabase(applicationContext))

        var event = intent.getSerializableExtra("event") as Event
        Glide.with(this).load(event.photo).centerCrop().into(binding.imgPhoto)
        binding.txtDesc.setText(event.description)
        binding.txtName.setText(event.name)
        binding.txtType.setText(event.typeEvent)
        var linearLayout = binding.lnrTable
        var relativeLayout = binding.relativeEvent
        var progressBar = ProgressDialog(this)
        progressBar.setTitle("Loading")
        progressBar.setMessage("Work in Progress")


        var detailviewmodel = EventDetailsViewModel(repo)
        detailviewmodel.updateSeatingMatrix(event.seating)
        detailviewmodel.message.observe(this){
            if(it.length>0){
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        }
        detailviewmodel.isBooked.observe(this){
            if(it){
                finish()
            }
        }


        detailviewmodel.isLoading.observe(this){
            if(it){
                progressBar.show()
            }else{
                progressBar.cancel()
            }
        }

        var chkBoxs = ArrayList<ArrayList<CheckBox>>()

        detailviewmodel.seatingMatrix.observe(this){
            if(chkBoxs.size>0) {
                for (i in 0..9) {
                    for (j in 0..14) {
                        if (detailviewmodel?.seatingMatrix?.value!![i][j] == 1) {
                            chkBoxs[i][j].isChecked = true
                            chkBoxs[i][j].isEnabled = false
                        }
                    }
                }
            }
        }

        for(i in 0..9){
            var newlinear = LinearLayout(this)
            var chks = ArrayList<CheckBox>()

            for( j in 0..14){
                var chk = CheckBox(this)
                chk.setLayoutParams(
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1f
                    )
                )
                chk.setOnClickListener {
                    if(detailviewmodel?.seatingMatrix?.value!![i][j] ===2){
                        detailviewmodel?.seatingMatrix?.value!![i][j]=0
                        detailviewmodel?.ticketsSelected?.value = detailviewmodel?.ticketsSelected?.value?.minus(
                            1
                        )
                    }else {
                        detailviewmodel?.seatingMatrix?.value!![i][j]=2
                        detailviewmodel?.ticketsSelected?.value = detailviewmodel?.ticketsSelected?.value?.plus(
                            1
                        )
                    }
                    binding.txtTickets.text = "Tickets : "+detailviewmodel?.ticketsSelected?.value.toString()

                }
                if(detailviewmodel?.seatingMatrix?.value!![i][j]==1){
                    chk.isChecked= true
                    chk.isEnabled=false
                }
                newlinear.addView(chk)
                chks.add(chk)
            }
            linearLayout.addView(newlinear)
            chkBoxs.add(chks)
        }

        binding.btnBook.setOnClickListener {
            detailviewmodel.bookTickets(event.eventId)
        }

    }
}

fun getSeatsListFromString(str:String): ArrayList<ArrayList<Int>> {
    var m =ArrayList<ArrayList<Int>>()
    var list:List<String>  = str.split(",")
    for ( i in list){
        var lst= i.split(":")
        var t = ArrayList<Int>()
        for (j in lst){
            t.add(j.toInt())
        }
        m.add(t)
    }
    return m
}

fun getStringFromSeatList(m:ArrayList<ArrayList<Int>>): String {
    var list= ArrayList<String>()
    for(i in m) {
        var str =i.joinToString(separator = ":")
        list.add(str)
    }
    return list.joinToString(separator = ",")
}