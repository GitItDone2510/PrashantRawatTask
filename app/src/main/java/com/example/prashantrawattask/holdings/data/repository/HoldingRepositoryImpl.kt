package com.example.prashantrawattask.holdings.data.repository

import com.example.prashantrawattask.holdings.data.remote.dto.Data
import com.example.prashantrawattask.holdings.domain.model.UserHoldingModel
import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel
import com.example.prashantrawattask.holdings.domain.repositories.HoldingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.collections.map
import kotlin.collections.orEmpty
import kotlin.collections.sumOf

class HoldingRepositoryImpl @Inject constructor(): HoldingRepository {

    override suspend fun getHoldings(): Flow<UserHoldingsDetailModel> {
        return flow { emit(UserHoldingsDetailModel(2.3, 3.4, 4.5, 5.6, emptyList())) }
    }

    fun getUserHoldingsDetails(data: Data?): UserHoldingsDetailModel {
        val holdings = data?.userHolding.orEmpty()

        val userHoldingModels = holdings.map { holding ->
            val ltp = holding.lastTradePrice ?: 0.0
            val avg = holding.avgPrice ?: 0.0
            val qty = holding.qty ?: 0
            val symbol = holding.symbol ?: ""

            UserHoldingModel(
                lastTradePrice = ltp,
                qty = qty,
                symbol = symbol,
                pnL = (ltp - avg) * qty
            )
        }

        val currentValue = holdings.sumOf { (it.lastTradePrice ?: 0.0) * (it.qty ?: 0) }
        val totalInvestment = holdings.sumOf { (it.avgPrice ?: 0.0) * (it.qty ?: 0) }
        val totalPnl = currentValue - totalInvestment
        val todayPnl =
            holdings.sumOf { ((it.closePrice ?: 0.0) - (it.lastTradePrice ?: 0.0)).times(it.qty ?: 0) }

        return UserHoldingsDetailModel(
            currentValue = currentValue,
            totalInvestment = totalInvestment,
            todayPnl = todayPnl,
            totalPnl = totalPnl,
            userHoldings = userHoldingModels
        )
    }
}