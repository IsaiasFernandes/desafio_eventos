package com.example.appeventos.data.repository

import com.example.appeventos.data.model.EventDetailsDTO
import com.example.appeventos.data.model.EventsDTO
import com.example.appeventos.data.service.EventService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class RepositoryImpl @Inject constructor(
    private var service: EventService
    ) : Repository {

    override suspend fun getEvents(): List<EventsDTO> =
        withContext(Dispatchers.IO) {
            try {
                service.getEvents()
            } catch (error: Exception) {
                throw error
            }
        }

    override suspend fun getDetailsEvents(id: String): EventDetailsDTO =
        withContext(Dispatchers.IO) {
            try {
                service.getDetailsEvents(id)
            } catch (error: Exception) {
                throw error
            }
    }
}