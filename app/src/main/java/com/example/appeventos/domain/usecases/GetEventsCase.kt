package com.example.appeventos.domain.usecases

import com.example.appeventos.data.repository.Repository
import com.example.appeventos.domain.map.EventsMapper
import com.example.appeventos.domain.model.Events
import dagger.Provides
import javax.inject.Inject


class GetEventsCase @Inject constructor(private var repository: Repository) {
    suspend fun execute(): List<Events> = EventsMapper(repository.getEvents())
}