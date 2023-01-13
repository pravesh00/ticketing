package com.example.ticketing.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Event(@PrimaryKey var eventId:String,@ColumnInfo(name="name") var name:String,@ColumnInfo(name="description")var description:String,@ColumnInfo(name="typeEvent") var typeEvent:String,@ColumnInfo(name="photo") var photo:String,@ColumnInfo(name="seating") var seating:String,@ColumnInfo(name="date") var date:String):
    Serializable {
}