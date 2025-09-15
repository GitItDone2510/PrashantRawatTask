package com.example.prashantrawattask.holdings.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prashantrawattask.common.Resource
import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel
import com.example.prashantrawattask.holdings.domain.repositories.HoldingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockHoldingsViewModel @Inject constructor(
    private val repository: HoldingRepository
) : ViewModel() {

    var state by mutableStateOf(StockHoldingState())
        private set

    init {
        getStockHoldings()
    }

    private fun getStockHoldings() {
        viewModelScope.launch {
            repository.getHoldings().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        state = state.copy(
                            isLoading = false,
                            userHoldingsDetails = result.data ?: UserHoldingsDetailModel.empty(),
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}