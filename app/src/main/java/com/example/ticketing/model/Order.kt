package com.example.ticketing.model

import androidx.room.Dao


data class Order(var orderId:String,var eventId:String,var status:String) {
}