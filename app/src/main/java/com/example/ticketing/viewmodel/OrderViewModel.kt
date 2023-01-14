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
        val lst:List<OrderDetails> = repo.getAllOrders().reversed()
        list.postValue(lst)
    }
    fun updateOrderData(param:String){
        val lst:List<OrderDetails> = repo.getAllOrders(param).reversed()
        list.postValue(lst)
    }
    fun updateOrderData(param1:String,param2: String){
        val lst:List<OrderDetails> = repo.getAllOrders(param1,param2).reversed()
        list.postValue(lst)
    }
    fun updateOrderDataStatus(status:String){
        val lst:List<OrderDetails> = repo.getAllOrdersStatus(status).reversed()
        list.postValue(lst)
    }
    fun updateOrderDataStatus(status:String,param:String){
        val lst:List<OrderDetails> = repo.getAllOrdersStatus(status,param).reversed()
        list.postValue(lst)
    }
    fun updateOrderDataStatus(status:String,param1:String,param2: String){
        val lst:List<OrderDetails> = repo.getAllOrdersStatus(status,param1,param2).reversed()
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