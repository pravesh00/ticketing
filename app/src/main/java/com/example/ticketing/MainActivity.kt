package com.example.ticketing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketing.adapter.EventAdapter
import com.example.ticketing.databinding.ActivityEventsBinding
import com.example.ticketing.databinding.ActivityMainBinding
import com.example.ticketing.model.Event

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        var list = ArrayList<Event>()
        var list1= ArrayList<Event>()
        list.add(Event("122","Wonder Women","Set in the 1980s, Wonder Woman`s next big screen adventure finds her facing two all-new foes, Max Lord and The Cheetah, and the unexpected return of a face from her past.","Movie","https://i.imgur.com/CxvVAcK.jpg"))
        list.add(Event("1122","Tom & Jerry","Infamous frenemies Tom and Jerry move to the city to start life anew. When Jerry moves into New York`s finest hotel, the event manager Kayla teams up with Tom to evict the mouse so that the `wedding of the century` can go off without a hitch. The result is one of the most elaborate cat-and-mouse games ever!","Movie","https://cdn.cinematerial.com/p/297x/osu2gdkh/tom-and-jerry-movie-poster-md.jpg"))
        list.add(Event("11222","Chaos Walking","In the not too distant future, Todd Hewitt discovers Viola, a mysterious girl who crash lands on his planet, where all the women have disappeared and the men are afflicted by \\\"the Noise\\\" - a force that puts all their thoughts on display. In this dangerous landscape, Viola`s life is threatened - and as Todd vows to protect her, he will have to discover his own inner power and unlock the planet`s dark secrets.","Movie","https://i.imgur.com/hEVn8dT.jpg"))
        list.add(Event("11222","Zakir Khan Standup special","In the not too distant future, Todd Hewitt discovers Viola, a mysterious girl who crash lands on his planet, where all the women have disappeared and the men are afflicted by \\\"the Noise\\\" - a force that puts all their thoughts on display. In this dangerous landscape, Viola`s life is threatened - and as Todd vows to protect her, he will have to discover his own inner power and unlock the planet`s dark secrets.","Comedy Show","https://www.dailypioneer.com/uploads/2021/story/images/big/zakir-khan-on-most-interesting-thing-about-being-stand-up-comic-2021-03-22.jpg"))
        list.add(Event("11222","Whats calling ur name?","In the not too distant future, Todd Hewitt discovers Viola, a mysterious girl who crash lands on his planet, where all the women have disappeared and the men are afflicted by \\\"the Noise\\\" - a force that puts all their thoughts on display. In this dangerous landscape, Viola`s life is threatened - and as Todd vows to protect her, he will have to discover his own inner power and unlock the planet`s dark secrets.","Play","https://i.imgur.com/hEVn8dT.jpg"))

        list1.addAll(list)
        var adapter = EventAdapter(list1)
        binding.recyclerEvents.adapter=adapter
        binding.recyclerEvents.layoutManager = LinearLayoutManager(this)

        fun filterList(){
            list1.clear()
            for( i in list){
                if(i.typeEvent=="Comedy Show" && binding.chkComedy.isChecked){
                    list1.add(i)
                }
                if(i.typeEvent=="Movie" && binding.chkMovie.isChecked){
                    list1.add(i)
                }
                if(i.typeEvent=="Play" && binding.chkPlay.isChecked){
                    list1.add(i)
                }
            }
            adapter.notifyDataSetChanged()

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


    }
}