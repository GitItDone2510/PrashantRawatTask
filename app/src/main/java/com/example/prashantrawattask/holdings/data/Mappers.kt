package com.example.prashantrawattask.holdings.data

import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity
import com.example.prashantrawattask.holdings.domain.model.UserHoldingModel
import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel
import kotlin.collections.map
import kotlin.collections.orEmpty
import kotlin.collections.sumOf

fun getUserHoldingsDetailModel(holdings: List<UserHoldingEntity>?): UserHoldingsDetailModel {
    val holdings = holdings.orEmpty()

    val userHoldingModels = holdings.map { holding ->
        val ltp = holding.lastTradePrice
        val avg = holding.avgPrice
        val qty = holding.qty
        val symbol = holding.symbol

        UserHoldingModel(
            lastTradePrice = ltp,
            qty = qty,
            symbol = symbol,
            pnL = (ltp - avg) * qty
        )
    }

    val currentValue = holdings.sumOf { it.lastTradePrice * it.qty }
    val totalInvestment = holdings.sumOf { it.avgPrice * it.qty }
    val totalPnl = currentValue - totalInvestment
    val todayPnl =
        holdings.sumOf { (it.closingPrice - it.lastTradePrice).times(it.qty) }

    return UserHoldingsDetailModel(
        currentValue = currentValue,
        totalInvestment = totalInvestment,
        todayPnl = todayPnl,
        totalPnl = totalPnl,
        userHoldings = userHoldingModels
    )
}
