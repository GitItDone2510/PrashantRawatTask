package com.example.prashantrawattask.common

import java.text.DecimalFormat

fun getFormattedAmounts(amount: Double): String {
    val df = DecimalFormat("#,##,##0.##" )
    return "₹${df.format(amount)}"
}

fun Double.toRupeeFormat(): String {
    val pattern = "#,##,##0.##" // Indian grouping
    val decimalFormat = DecimalFormat(pattern)
    val absValue = decimalFormat.format(kotlin.math.abs(this))
    return if (this < 0) "-₹$absValue" else "₹$absValue"
}