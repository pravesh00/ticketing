package com.example.ticketing.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ticketing.model.Event
import com.example.ticketing.model.Order
import com.example.ticketing.model.OrderDetails

@Dao
interface TicketingDao {
    @Insert
    fun insertNewOrder( order: Order)

    @Insert
    fun insertNewEvent(event :Event)

    @Query("Select * from event")
    fun getAllEvents(): List<Event>

    @Query("Update ordersdata set status='Cancelled' where orderId = :id")
    fun cancelOrder(id:String)

    @Query("Select * from ordersdata o JOIN event e ON o.eventId = e.eventId")
    fun getAllOrders(): List<OrderDetails>

    @Insert
    fun insertAllValues(lst:ArrayList<Event>)

    @Query("Update event set seating = :seat where eventId=:id")
    fun updateSeatingByEventId(seat:String,id:String)

    @Query("Select * from event where typeEvent = :param1 or typeEvent = :param2")
    fun getAllEvents(param1:String,param2:String):List<Event>

    @Query("Select * from event where typeEvent =:param1")
    fun getAllEvents(param1:String):List<Event>

    @Query("Select * from event")
    fun getAllEvents(param1:String, param2:String, param3:String):List<Event>


}