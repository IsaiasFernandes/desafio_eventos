package com.example.appeventos.domain.map

import com.example.appeventos.data.model.EventsDTO
import com.example.appeventos.domain.model.Events
import com.example.appeventos.domain.utils.formatPrice
import com.example.appeventos.domain.utils.toDate

fun EventsMapper(eventsDTO: List<EventsDTO>) : List<Events> {
    return eventsDTO.map {
        Events(
            id = it.id,
            image = it.image,
            price = it.price.formatPrice(),
            title = it.title,
            description = it.description
        )
    }
}