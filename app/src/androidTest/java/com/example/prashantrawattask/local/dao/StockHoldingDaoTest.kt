package com.example.prashantrawattask.local.dao

import com.example.prashantrawattask.DatabaseTest
import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class StockHoldingDaoTest: DatabaseTest() {

    @Test
    fun insertAndGetHoldings_returnsCorrectData() = runTest {
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
        stockHoldingDao.insertStockHoldings(holdings)
        val result = stockHoldingDao.getAllHoldings().first()

        // Assert
        Assert.assertEquals(2, result.size)
        Assert.assertEquals("ICICI", result[0].symbol)
        Assert.assertEquals(10, result[0].qty)
    }

    @Test
    fun deleteAllHoldings_clearsTable() = runTest {
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
        stockHoldingDao.insertStockHoldings(holdings)
        stockHoldingDao.deleteAllHoldings()
        // Assert
        val result = stockHoldingDao.getAllHoldings().first()
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun clearAndInsertAll_replacesData() = runTest {
        val oldHoldings = listOf(
            UserHoldingEntity(
                symbol = "ICICI",
                qty = 10,
                avgPrice = 100.0,
                lastTradePrice = 120.0,
                closingPrice = 115.0
            )
        )
        stockHoldingDao.insertStockHoldings(oldHoldings)

        val newHoldings = listOf(
            UserHoldingEntity(symbol = "HDFC",
                qty = 20,
                avgPrice = 150.0,
                lastTradePrice = 120.0,
                closingPrice = 155.0)
        )
        stockHoldingDao.clearAndInsertAll(newHoldings)

        val result = stockHoldingDao.getAllHoldings().first()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("HDFC", result[0].symbol)
    }

    @Test
    fun getAllHoldings_emitsUpdates() = runTest {
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
        stockHoldingDao.insertStockHoldings(holdings)
        val result = stockHoldingDao.getAllHoldings().first()
        // Assert
        Assert.assertEquals(1, result.size)
        Assert.assertEquals("ICICI", result[0].symbol)
    }
}