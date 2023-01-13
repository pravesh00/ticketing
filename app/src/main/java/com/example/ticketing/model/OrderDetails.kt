package com.example.ticketing.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderDetails(@PrimaryKey var orderId:String, @ColumnInfo(name="eventId")var eventId:String, @ColumnInfo(name="status") var status:String, @ColumnInfo(name="orderSeats") var orderSeats:String,@ColumnInfo(name="name") var name:String,@ColumnInfo(name="description")var description:String,@ColumnInfo(name="typeEvent") var typeEvent:String,@ColumnInfo(name="photo") var photo:String,@ColumnInfo(name="seating") var seating:String,@ColumnInfo(name="date") var date:String,@ColumnInfo(name="userId") var userId:String) {
}