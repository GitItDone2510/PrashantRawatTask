package com.example.prashantrawattask.holdings.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserHoldingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
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