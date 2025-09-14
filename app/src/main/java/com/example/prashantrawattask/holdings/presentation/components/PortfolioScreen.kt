package com.example.prashantrawattask.holdings.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.prashantrawattask.holdings.presentation.StockHoldingState

@Composable
fun PortfolioScreen(modifier: Modifier = Modifier, state: StockHoldingState, listState: LazyListState) {

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(state = listState) {
            itemsIndexed(state.userHoldingsDetails.userHoldings) { index, holding ->
                StockHoldingView(
                    modifier = Modifier.fillMaxWidth(),
                    holding = holding
                )
                if (index < state.userHoldingsDetails.userHoldings.lastIndex) {
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}