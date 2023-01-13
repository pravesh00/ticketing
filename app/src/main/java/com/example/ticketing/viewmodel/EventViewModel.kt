package com.example.ticketing.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketing.model.Event
import com.example.ticketing.room.TicketingDatabase
import com.example.ticketing.room.TicketingRepository
import java.security.spec.ECField

class EventViewModel(
    val repo:TicketingRepository
): ViewModel() {
    val list = MutableLiveData<List<Event>>()

    fun updateEventData(){
        val lst:List<Event> = repo.getAllEvents()
        list.postValue(lst)
    }

    fun prePopulateEventData(){
        val lst= ArrayList<Event>()
        var seating= "0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0,0:0:0:0:0:0:0:0:0:0:0:0:0:0:0"
        lst.add(Event("A1","Wonder Women","Set in the 1980s, Wonder Woman`s next big screen adventure finds her facing two all-new foes, Max Lord and The Cheetah, and the unexpected return of a face from her past.","Movie","https://i.imgur.com/CxvVAcK.jpg",seating,""))
        lst.add(Event("B1","Tom & Jerry","Infamous frenemies Tom and Jerry move to the city to start life anew. When Jerry moves into New York`s finest hotel, the event manager Kayla teams up with Tom to evict the mouse so that the `wedding of the century` can go off without a hitch. The result is one of the most elaborate cat-and-mouse games ever!","Movie","https://cdn.cinematerial.com/p/297x/osu2gdkh/tom-and-jerry-movie-poster-md.jpg",seating,""))
        lst.add(Event("C1","Chaos Walking","In the not too distant future, Todd Hewitt discovers Viola, a mysterious girl who crash lands on his planet, where all the women have disappeared and the men are afflicted by \\\"the Noise\\\" - a force that puts all their thoughts on display. In this dangerous landscape, Viola`s life is threatened - and as Todd vows to protect her, he will have to discover his own inner power and unlock the planet`s dark secrets.","Movie","https://i.imgur.com/hEVn8dT.jpg",seating,""))
        lst.add(Event("D1","Zakir Khan Standup special","In the not too distant future, Todd Hewitt discovers Viola, a mysterious girl who crash lands on his planet, where all the women have disappeared and the men are afflicted by \\\"the Noise\\\" - a force that puts all their thoughts on display. In this dangerous landscape, Viola`s life is threatened - and as Todd vows to protect her, he will have to discover his own inner power and unlock the planet`s dark secrets.","Comedy Show","https://www.dailypioneer.com/uploads/2021/story/images/big/zakir-khan-on-most-interesting-thing-about-being-stand-up-comic-2021-03-22.jpg",seating,""))
        lst.add(Event("E1","Whats calling ur name?","In the not too distant future, Todd Hewitt discovers Viola, a mysterious girl who crash lands on his planet, where all the women have disappeared and the men are afflicted by \\\"the Noise\\\" - a force that puts all their thoughts on display. In this dangerous landscape, Viola`s life is threatened - and as Todd vows to protect her, he will have to discover his own inner power and unlock the planet`s dark secrets.","Play","https://i.imgur.com/hEVn8dT.jpg",seating,""))
        repo.insertallevents(lst)
    }

    fun clearDatabase(){
        repo.clearDatabase()
    }
}