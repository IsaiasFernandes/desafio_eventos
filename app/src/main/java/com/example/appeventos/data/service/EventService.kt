package com.example.appeventos.data.service

import com.example.appeventos.data.model.EventsDTO
import retrofit2.http.GET

interface EventService {
    @GET("events")
    suspend fun getEvents() : List<EventsDTO>

}