package com.example.appeventos.domain.model

data class EventDetails(
    val id: String,
    val date: String,
    val title: String,
    val image: String,
    val price: String,
    val description: String,
    val longitude: String,
    val latitude: String
)
