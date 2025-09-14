package com.example.prashantrawattask.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.prashantrawattask.common.ui.theme.TaskTheme
import com.example.prashantrawattask.holdings.presentation.StockHoldingsViewModel

@Composable
fun TaskApp(viewModel: StockHoldingsViewModel = hiltViewModel()) {
    TaskTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            TaskAppNavigationActions(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: TaskAppDestinations.Portfolio().route

        Scaffold(
            contentWindowInsets = WindowInsets.systemBars,
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF012144))
                        .padding(WindowInsets.statusBars.asPaddingValues()),
                    icon = Icons.Default.AccountCircle,
                    title = "Portfolio"
                )
            },
            bottomBar = {
                NavigationBar {
                    TaskAppDestinations.getAllDestinations().forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = destination.route == currentRoute,
                            onClick = {
                                when (destination.route) {
                                    WATCHLIST_ROUTE -> navigationActions.navigateToWatchlist()
                                    ORDERS_ROUTE -> navigationActions.navigateToOrders()
                                    PORTFOLIO_ROUTE -> navigationActions.navigateToPortfolio()
                                    FUNDS_ROUTE -> navigationActions.navigateToFunds()
                                    INVEST_ROUTE -> navigationActions.navigateToInvest()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.label,
                                )
                            },
                            label = { Text(destination.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF012144),
                                unselectedIconColor = Color.Gray,
                                selectedTextColor = Color(0xFF012144),
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        ) { contentPadding ->
            TaskAppNavGraph(
                modifier = Modifier.padding(contentPadding),
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun CustomBottomIcon(
    isSelected: Boolean,
    onItemSelected: () -> Unit,
    icon: ImageVector,
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clickable { onItemSelected() },
        contentAlignment = Alignment.Center
    ) {
        // Top indicator (attached to the item, not inside icon)
        if (isSelected) {
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .height(3.dp)
                    .fillMaxWidth(0.4f) // adjust width
                    .background(Color.Blue, RoundedCornerShape(2.dp))
            )
        }

        // Icon + label stacked
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "item",
                tint = if (isSelected) Color.Blue else Color.Gray
            )
            Text(
                text = text,
                color = if (isSelected) Color.Blue else Color.Gray,
            )
        }
    }
}