package com.example.prashantrawattask.holdings.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StockResponse(
    @SerializedName("data")
    val data: Data?
)

data class Data(
    @SerializedName("userHolding")
    val userHolding: List<UserHolding>?
)

data class UserHolding(
    @SerializedName("avgPrice")
    val avgPrice: Double? = 0.0,
    @SerializedName("close")
    val closePrice: Double? = 0.0,
    @SerializedName("ltp")
    val lastTradePrice: Double? = 0.0,
    @SerializedName("quantity")
    val qty: Int? = 0,
    @SerializedName("symbol")
    val symbol: String?
)