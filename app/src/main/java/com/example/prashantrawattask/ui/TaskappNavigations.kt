package com.example.prashantrawattask.ui

import androidx.navigation.NavController
import androidx.navigation.NavHostController

object TaskAppDestinations {
    const val WATCHLIST_ROUTE = "watchlist"
    const val ORDERS_ROUTE = "orders"
    const val PORTFOLIO_ROUTE = "portfolio"
    const val FUNDS_ROUTE = "funds"
    const val INVEST_ROUTE = "invest"
}

class TaskAppNavigationActions(navController: NavHostController) {

    val navigateToWatchlist: () -> Unit = {
        navController.navigate(TaskAppDestinations.WATCHLIST_ROUTE)
    }
    val navigateToOrders: () -> Unit = {
        navController.navigate(TaskAppDestinations.ORDERS_ROUTE)
    }
    val navigateToPortfolio: () -> Unit = {
        navController.navigate(TaskAppDestinations.PORTFOLIO_ROUTE)
    }
    val navigateToFunds: () -> Unit = {
        navController.navigate(TaskAppDestinations.FUNDS_ROUTE)
    }
    val navigateToInvest: () -> Unit = {
        navController.navigate(TaskAppDestinations.INVEST_ROUTE)
    }
}