package com.example.appeventos.domain.utils

import java.text.SimpleDateFormat
import java.util.Date

fun Int.toDate(): String {
    val date = Date(this.toLong())
    val formatDate = SimpleDateFormat("dd MMMM yyyy HH:mm")
    return formatDate.format(date)
}

fun Double.formatPrice(): String {
    val (firstPrice, lastPrice)  = this.toString().split(".")
    return "R$ ${firstPrice},${lastPrice}"
}