package com.example.prashantrawattask.holdings.domain.model

data class UserHoldingsDetailModel(
    val currentValue: Double,
    val totalInvestment: Double,
    val todayPnl: Double,
    val totalPnl: Double,
    val userHoldings: List<UserHoldingModel>
) {

    companion object {
        fun empty() = UserHoldingsDetailModel(
            currentValue = 0.0,
            totalInvestment = 0.0,
            todayPnl = 0.0,
            totalPnl = 0.0,
            userHoldings = emptyList()
        )
    }
}
