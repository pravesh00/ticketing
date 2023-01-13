package com.example.ticketing.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketing.room.TicketingRepository

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
            for (i in 0..9) {
                for (j in 0..14) {
                    if (seatingMatrix?.value!![i][j] == 2) {
                        seatingMatrix?.value!![i][j] = 1
                    }
                }
            }
            var newStringSeating = seatingMatrix.value?.let { getStringFromList(it) }
            if (newStringSeating != null) {
                repo.updateSeatingPlan(newStringSeating, id)
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
}