package com.example.prashantrawattask.holdings.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class UserHoldingEntity(
    @ColumnInfo("avgPrice")
    val avgPrice: Double,
    @ColumnInfo("closePrice")
    val closingPrice: Double,
    @ColumnInfo("lastTradePrice")
    val lastTradePrice: Double,
    @ColumnInfo("quantity")
    val qty: Int,
    @ColumnInfo("symbol")
    val symbol: String
)