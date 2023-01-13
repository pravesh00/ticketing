package com.example.ticketing.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ticketing.model.OrderDetails
import com.example.ticketing.room.TicketingRepository

class OrderViewModel(
    val repo:TicketingRepository
) : ViewModel(){
    val list = MutableLiveData<List<OrderDetails>>()

    fun updateOrderData(){
        val lst:List<OrderDetails> = repo.getAllOrders()
        list.postValue(lst)
    }

    fun cancelTicket(id:String,orderSeats:String,seating:String,eventId:String){
        try {
            var lst:ArrayList<ArrayList<Int>> = getSeatsListFromString(orderSeats)
            var seatPlan:ArrayList<ArrayList<Int>> = getSeatsListFromString(seating)
            for (i in lst) {
                seatPlan[i[0]][i[1]] = 0
            }
            var stringnew = getStringFromSeatList(seatPlan)
            repo.updateSeatingPlan(stringnew, eventId)
            repo.cancelOrder(id)
            updateOrderData()
        }catch (e:Exception){
            Log.d("failure",e.message.toString())
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

}