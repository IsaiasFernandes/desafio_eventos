package com.example.appeventos.data.model

data class EventDetailsDTO(
    val id: String,
    val date: Number,
    val title: String,
    val image: String,
    val price: Double,
    val description: String,
    val longitude: Double,
    val latitude: Double
)
