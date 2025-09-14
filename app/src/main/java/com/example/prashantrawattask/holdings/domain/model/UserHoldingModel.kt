package com.example.prashantrawattask.holdings.domain.model

data class UserHoldingModel(
    val lastTradePrice: Double,
    val qty: Int,
    val symbol: String,
    val pnL: Double,
)
