package com.example.prashantrawattask.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prashantrawattask.holdings.presentation.StockHoldingsViewModel

@Composable
fun TaskAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = TaskAppDestinations.Portfolio().route,
    viewModel: StockHoldingsViewModel
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(TaskAppDestinations.Watchlist().route) {
            PlaceHolderScreen(screenName = "WatchList Screen")
        }
        composable(TaskAppDestinations.Orders().route) {
            PlaceHolderScreen(screenName = "Orders Screen")
        }
        composable(TaskAppDestinations.Portfolio().route) {
            PlaceHolderScreen(screenName = "Portfolio Screen")
        }
        composable(TaskAppDestinations.Funds().route) {
            PlaceHolderScreen(screenName = "Funds Screen")
        }
        composable(TaskAppDestinations.Invest().route) {
            PlaceHolderScreen(screenName = "Invest Screen")
        }
    }
}

@Composable
fun PlaceHolderScreen(
    modifier: Modifier = Modifier,
    screenName: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = screenName
        )
    }
}