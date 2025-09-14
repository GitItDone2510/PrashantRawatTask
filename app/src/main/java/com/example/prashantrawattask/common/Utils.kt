package com.example.prashantrawattask.common

import java.text.DecimalFormat

fun getFormattedAmounts(amount: Double): String {
    val value = 123.4567
    val df = DecimalFormat("#.##")
    return "₹${df.format(value)}"
}

fun Double.toRupeeString(): String {
    val decimalFormat = DecimalFormat("#.##")
    val absValue = decimalFormat.format(kotlin.math.abs(this)) // absolute value
    return if (this < 0) "-₹$absValue" else "₹$absValue"
}