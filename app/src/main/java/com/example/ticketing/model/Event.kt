package com.example.ticketing.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.PrimaryKey


@Dao
data class Event(@PrimaryKey var eventId:String,@ColumnInfo(name="name") var name:String,@ColumnInfo(name="description") var description:String) {

}