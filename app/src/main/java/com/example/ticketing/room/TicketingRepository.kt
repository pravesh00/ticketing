package com.example.ticketing.room

import com.example.ticketing.model.Event
import com.example.ticketing.model.Order

class TicketingRepository(
    private val database: TicketingDatabase
) {
    fun insertNewOrder(order: Order) = database.getTicketingDao().insertNewOrder(order)
    fun insertNewEvent(event: Event) = database.getTicketingDao().insertNewEvent(event)
    fun getAllEvents() = database.getTicketingDao().getAllEvents()
    fun getAllEvents(param1:String) = database.getTicketingDao().getAllEvents(param1)
    fun getAllEvents(param1:String,param2:String) = database.getTicketingDao().getAllEvents(param1,param2)
    //fun getAllEvents(param1:String,param2:String,param3:String) = database.getTicketingDao().getAllEvents(param1,param2,param3)
    fun getAllOrders() = database.getTicketingDao().getAllOrders()
    fun cancelOrder(id:String) = database.getTicketingDao().cancelOrder(id)
    fun insertallevents(lst:ArrayList<Event>) = database.getTicketingDao().insertAllValues(lst)
    fun updateSeatingPlan(seat:String, id:String)= database.getTicketingDao().updateSeatingByEventId(seat,id)
    fun clearDatabase() = database.clearAllTables()
}