package com.example.ticketing.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketing.getSeatsListFromString
import com.example.ticketing.model.Order
import com.example.ticketing.room.TicketingRepository
import kotlin.random.Random

class EventDetailsViewModel(
    val repo:TicketingRepository
): ViewModel() {
    var seatingMatrix = MutableLiveData<Array<Array<Int>>>(Array(10) {Array(15) {0} })
    var ticketsSelected = MutableLiveData<Int>(0)
    var isLoading = MutableLiveData<Boolean>(false)
    var isBooked = MutableLiveData<Boolean>(false)
    var message = MutableLiveData<String>("")

    fun updateSeatingMatrix(str:String){
        seatingMatrix.postValue(getListFromString(str))
    }

    fun bookTickets(id:String){
        isLoading.postValue(true)
        try {
            var seatss= ArrayList<ArrayList<Int>>()
            for (i in 0..9) {
                for (j in 0..14) {
                    if (seatingMatrix?.value!![i][j] == 2) {
                        var lsst= ArrayList<Int>()
                        lsst.add(i)
                        lsst.add(j)
                        seatss.add(lsst)
                        seatingMatrix?.value!![i][j] = 1
                    }
                }
            }
            var orderedSeats = getStringFromSeatList(seatss)
            var newStringSeating = seatingMatrix.value?.let { getStringFromList(it) }
            if (newStringSeating != null) {
                repo.updateSeatingPlan(newStringSeating, id)
                var rndm = (1..100000).random()
                repo.insertNewOrder(Order(rndm.toString(),id,"Confirmed",orderedSeats,""))
            }
        }catch(e:Exception){
            isLoading.postValue(false)
            message.postValue("Error Occured")
        }finally {
            isLoading.postValue(false)
            isBooked.postValue(true)
            message.postValue("Tickets are booked")
        }

    }

    fun getStringFromList(m:Array<Array<Int>>): String {
        var list= ArrayList<String>()
        for(i in 0..9) {
            var str =m[i].joinToString(separator = ":")
            list.add(str)
        }
        return list.joinToString(separator = ",")
    }
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

    fun getStringFromSeatList(m:ArrayList<ArrayList<Int>>): String {
        var list= ArrayList<String>()
        for(i in m) {
            var str =i.joinToString(separator = ":")
            list.add(str)
        }
        return list.joinToString(separator = ",")
    }
}