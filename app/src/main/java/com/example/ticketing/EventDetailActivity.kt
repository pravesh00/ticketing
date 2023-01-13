package com.example.ticketing

import android.R.string
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import android.widget.CheckBox
import android.widget.LinearLayout
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


//Type conversion ti store in
fun getListFromString(str:String): Array<Array<Int>> {
    var m = Array(10) {Array(15) {0} }
    var list:List<String>  = str.split(",")
    for(i in 0..9){
        var cur=list[i]
        var lst:List<String> = cur.split(":")
        for(j in 0..14){
            m[i][j]= lst[j].toInt()
        }
    }
    return m
}

fun getStringFromList(m:Array<Array<Int>>): String {
    var list= ArrayList<String>()
    for(i in 0..9) {
        var str =m[i].joinToString(separator = ":")
        list.add(str)
    }
    return list.joinToString(separator = ",")
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