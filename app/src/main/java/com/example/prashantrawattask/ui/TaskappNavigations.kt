package com.example.prashantrawattask.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

const val WATCHLIST_ROUTE = "watchlist"
const val ORDERS_ROUTE = "orders"
const val PORTFOLIO_ROUTE = "portfolio"
const val FUNDS_ROUTE = "funds"
const val INVEST_ROUTE = "invest"

sealed class TaskAppDestinations(val label: String, val icon: ImageVector, val route: String) {
    class Watchlist: TaskAppDestinations("Watchlist", Icons.AutoMirrored.Filled.List, WATCHLIST_ROUTE)
    class Orders: TaskAppDestinations("Orders", Icons.Default.ShoppingCart, ORDERS_ROUTE)
    class Portfolio: TaskAppDestinations("Portfolio", Icons.Default.DateRange, PORTFOLIO_ROUTE)
    class Funds: TaskAppDestinations("Funds", Icons.Default.Place, FUNDS_ROUTE)
    class Invest: TaskAppDestinations("Invest", Icons.Default.Refresh, INVEST_ROUTE)

    companion object {
        fun getAllDestinations(): List<TaskAppDestinations> {
            return listOf(Watchlist(), Orders(), Portfolio(), Funds(), Invest())
        }
    }
}

class TaskAppNavigationActions(navController: NavHostController) {

    val navigateToWatchlist: () -> Unit = {
        navController.navigate(TaskAppDestinations.Watchlist().route)
    }
    val navigateToOrders: () -> Unit = {
        navController.navigate(TaskAppDestinations.Orders().route)
    }
    val navigateToPortfolio: () -> Unit = {
        navController.navigate(TaskAppDestinations.Portfolio().route)
    }
    val navigateToFunds: () -> Unit = {
        navController.navigate(TaskAppDestinations.Funds().route)
    }
    val navigateToInvest: () -> Unit = {
        navController.navigate(TaskAppDestinations.Invest().route)
    }
}