package com.example.prashantrawattask.holdings.data

import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlin.test.Test

class MappersTest {

    @Test
    fun getUserHoldingsDetailModel_whenEmptyList_returnsZeroedValues() {
        val result = getUserHoldingsDetailModel(emptyList())

        assertEquals(0.0, result.currentValue, 0.001)
        assertEquals(0.0, result.totalInvestment, 0.001)
        assertEquals(0.0, result.totalPnl, 0.001)
        assertEquals(0.0, result.todayPnl, 0.001)
        assertTrue(result.userHoldings.isEmpty())
    }

    @Test
    fun getUserHoldingsDetailModel_whenSingleItem_returnsCorrectValues() {
        // Arrange
        val holdings = listOf(
            UserHoldingEntity(
                symbol = "ICICI",
                qty = 10,
                avgPrice = 100.0,
                lastTradePrice = 120.0,
                closingPrice = 115.0
            )
        )
        // Act
        val result = getUserHoldingsDetailModel(holdings)

        // Assert
        assertEquals(1200.0, result.currentValue, 0.001)
        assertEquals(1000.0, result.totalInvestment, 0.001)
        assertEquals(200.0, result.totalPnl, 0.001)
        assertEquals(-50.0, result.todayPnl, 0.001)
        assertEquals(200.0, result.userHoldings.first().pnL, 0.001)
    }

    @Test
    fun getUserHoldingsDetailModel_whenMultipleItems_returnsCorrectValues() {
        // Arrange
        val holdings = listOf(
            UserHoldingEntity(
                symbol = "ICICI",
                qty = 10,
                avgPrice = 100.0,
                lastTradePrice = 120.0,
                closingPrice = 115.0
            ),
            UserHoldingEntity(
                symbol = "HDFC",
                qty = 5,
                avgPrice = 200.0,
                lastTradePrice = 180.0,
                closingPrice = 185.0
            )
        )
        // Act
        val result = getUserHoldingsDetailModel(holdings)
        // Assert
        assertEquals(1200.0 + 900.0, result.currentValue, 0.001)
        assertEquals(1000.0 + 1000.0, result.totalInvestment, 0.001)
        assertEquals(100.0, result.totalPnl, 0.001)
        assertEquals(-50.0 + 25.0, result.todayPnl, 0.001)
        assertEquals(2, result.userHoldings.size)
    }

    @Test
    fun getUserHoldingsDetailModel_whenNegativePnL_returnsCorrectValues() {
        // Arrange
        val holdings = listOf(
            UserHoldingEntity(
                symbol = "HDFC",
                qty = 10,
                avgPrice = 150.0,
                lastTradePrice = 120.0,
                closingPrice = 125.0
            )
        )
        // Act
        val result = getUserHoldingsDetailModel(holdings)
        // Assert
        assertEquals(1200.0, result.currentValue, 0.001)
        assertEquals(1500.0, result.totalInvestment, 0.001)
        assertEquals(-300.0, result.totalPnl, 0.001)
        assertEquals(50.0, result.todayPnl, 0.001)
        assertEquals(-300.0, result.userHoldings.first().pnL, 0.001)
    }
}