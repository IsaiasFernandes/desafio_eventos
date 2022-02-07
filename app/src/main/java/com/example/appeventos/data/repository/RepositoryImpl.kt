package com.example.appeventos.data.repository

import com.example.appeventos.data.model.EventsDTO
import com.example.appeventos.data.service.EventService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}