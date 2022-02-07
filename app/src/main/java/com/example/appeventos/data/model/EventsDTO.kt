package com.example.appeventos.data.model

data class EventsDTO(
    val id: String,
    val date: Int,
    val image: String,
    val price: Double,
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val people: List<PeopleDTO>
)
