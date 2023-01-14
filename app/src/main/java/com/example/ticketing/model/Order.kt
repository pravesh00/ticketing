package com.example.ticketing.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ordersdata")
data class Order(@PrimaryKey var orderId:String, @ColumnInfo(name="eventId")var eventId:String,@ColumnInfo(name="status") var status:String,@ColumnInfo(name="orderSeats") var orderSeats:String,@ColumnInfo(name="userId") var userId:String) {
}