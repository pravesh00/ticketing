package com.example.ticketing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ticketing.room.TicketingRepository

class EventDetailsViewModelFactory (
    private val repository: TicketingRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(EventDetailsViewModel::class.java)){
                return EventDetailsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown View Model class")
        }
}