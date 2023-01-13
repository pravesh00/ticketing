package com.example.ticketing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ticketing.room.TicketingRepository


class EventViewModelFactory(
    private val repository: TicketingRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java)){
            return EventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}