package com.example.appeventos.data.repository

import com.example.appeventos.data.model.EventsDTO

interface Repository {
    suspend fun getEvents(): List<EventsDTO>
}