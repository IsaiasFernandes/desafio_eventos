package com.example.appeventos.domain.usecases

import com.example.appeventos.data.repository.Repository
import com.example.appeventos.domain.map.EventDetailsMapper
import com.example.appeventos.domain.model.EventDetails
import javax.inject.Inject

class GetEventDetailsCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(id: String): EventDetails = EventDetailsMapper(repository.getDetailsEvents(id))
}