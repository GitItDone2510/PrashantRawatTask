package com.example.prashantrawattask.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.prashantrawattask.ui.theme.TaskTheme

@Composable
fun TaskApp() {
    TaskTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            TaskAppNavigationActions(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: TaskAppDestinations.PORTFOLIO_ROUTE

    }
}