package com.example.appeventos.data.service

import com.example.appeventos.data.model.EventDetailsDTO
import com.example.appeventos.data.model.EventsDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {

    @GET("events")
    suspend fun getEvents() : List<EventsDTO>

    @GET("events/{id}")
    suspend fun getDetailsEvents(@Path("id") id: String): EventDetailsDTO



}