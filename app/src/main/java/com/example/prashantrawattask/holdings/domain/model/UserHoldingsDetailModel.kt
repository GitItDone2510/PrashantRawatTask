package com.example.prashantrawattask.holdings.domain.model

data class UserHoldingsDetailModel(
    val currentValue: Double,
    val totalInvestment: Double,
    val todayPnl: Double,
    val totalPnl: Double,
    val userHoldings: List<UserHoldingModel>
)
