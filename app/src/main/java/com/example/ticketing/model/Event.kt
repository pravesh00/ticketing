package com.example.ticketing.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.PrimaryKey


data class Event(var eventId:String,var name:String,var description:String, var typeEvent:String, var photo:String) {

}