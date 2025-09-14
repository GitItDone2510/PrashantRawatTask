package com.example.prashantrawattask.holdings.presentation

import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel

data class StockHoldingState(
    val isLoading: Boolean = false,
    val userHoldingsDetails: UserHoldingsDetailModel = UserHoldingsDetailModel.empty(),
    val error: String = ""
)
