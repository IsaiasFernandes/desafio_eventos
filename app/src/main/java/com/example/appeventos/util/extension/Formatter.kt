package com.example.appeventos.domain.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Number.toDate(): String {
    val date = Date(this.toLong())
    val formatDate = SimpleDateFormat("dd MMMM yyyy HH:mm")
    return formatDate.format(date)
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}