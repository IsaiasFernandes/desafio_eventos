package com.example.appeventos.domain.map

import com.example.appeventos.data.model.EventDetailsDTO
import com.example.appeventos.domain.model.EventDetails
import com.example.appeventos.domain.utils.formatPrice
import com.example.appeventos.domain.utils.toDate
import java.text.SimpleDateFormat
import java.util.Date

fun EventDetailsMapper(eventDetailsDTO: EventDetailsDTO) : EventDetails {
    return  EventDetails(
        id = eventDetailsDTO.id,
        date = eventDetailsDTO.date.toDate(),
        title = eventDetailsDTO.title,
        image = eventDetailsDTO.image,
        price = eventDetailsDTO.price.formatPrice(),
        description = eventDetailsDTO.description,
        longitude = eventDetailsDTO.longitude.toString(),
        latitude = eventDetailsDTO.latitude.toString()
    )
}