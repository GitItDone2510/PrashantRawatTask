package com.example.prashantrawattask.holdings.domain.repositories

import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel
import kotlinx.coroutines.flow.Flow

interface HoldingRepository {

    suspend fun getHoldings(): Flow<UserHoldingsDetailModel>
}