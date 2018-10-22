package com.giuseppesorce.common

import java.text.SimpleDateFormat
import java.util.*

fun Date?.completeDay(): String {
    this ?: return ""
    val formatDay = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatDay.format(this)
}

fun Date?.completeDayMonthNum(): String {
    this ?: return ""
    val formatDay = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatDay.format(this)
}

fun Date?.DayMonthNum(): String {
    this ?: return ""
    val formatDay = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatDay.format(this)
}

fun Date?.Hours(): String {
    this ?: return ""
    val formatDay = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatDay.format(this)
}

fun Date?.completeDateString(): String {
    this ?: return ""
    val formatDay = SimpleDateFormat("dd MMM yyyy hh:mm", Locale.getDefault())
    return formatDay.format(this)
}