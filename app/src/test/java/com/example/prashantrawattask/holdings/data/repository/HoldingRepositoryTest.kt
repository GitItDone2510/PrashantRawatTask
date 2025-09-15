package com.example.prashantrawattask.holdings.data.repository

import com.example.prashantrawattask.MainDispatcherRule
import com.example.prashantrawattask.common.Resource
import com.example.prashantrawattask.holdings.data.local.dao.StockHoldingDao
import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity
import com.example.prashantrawattask.holdings.data.remote.StockApi
import com.example.prashantrawattask.holdings.data.remote.dto.Data
import com.example.prashantrawattask.holdings.data.remote.dto.StockResponse
import com.example.prashantrawattask.holdings.data.remote.dto.UserHolding
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.test.Test


@OptIn(ExperimentalCoroutinesApi::class)
class HoldingRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var SUT: HoldingRepositoryImpl
    private val stockHoldingDao: StockHoldingDao = mockk(relaxed = true)
    private val stockApi: StockApi = mockk()


    @Before
    fun setup() {
        SUT = HoldingRepositoryImpl(stockHoldingDao, stockApi)
    }

    @Test
    fun getHoldings_returnLoadingThenSuccess_whenSuccess() = runTest {
        // Arrange
        val fakeResponse = StockResponse(
            data = Data(
                userHolding = listOf(
                    UserHolding(
                        avgPrice = 100.0,
                        closePrice = 110.0,
                        lastTradePrice = 95.0,
                        qty = 50,
                        symbol = "TCS"
                    )
                )
            )
        )
        coEvery { stockApi.getHoldings(any()) } returns fakeResponse
        coEvery { stockHoldingDao.clearAndInsertAll(any()) } just Runs
        coEvery { stockHoldingDao.getAllHoldings() } returns flowOf(
            listOf(
                UserHoldingEntity(
                    avgPrice = 100.0,
                    closingPrice = 110.0,
                    lastTradePrice = 95.0,
                    qty = 50,
                    symbol = "TCS"
                )
            )
        )

        // Act
        val emissions = SUT.getHoldings().toList()

        // Assert
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success)
        val success = emissions[1] as Resource.Success
        assert(success.data?.userHoldings?.size == 1)
    }

    @Test
    fun getHoldings_emitsError_onHttpException() = runTest {
        // Arrange
        coEvery { stockApi.getHoldings(any()) } throws HttpException(
            Response.error<Any>(
                400,
                "Bad Request".toResponseBody()
            )
        )

        // Act
        val emissions = SUT.getHoldings().toList()

        // Assert
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)
    }

    @Test
    fun getHolding_emitsError_onIOException() = runTest {
        // Arrange
        coEvery { stockApi.getHoldings(any()) } throws IOException("No internet")

        // Act
        val emissions = SUT.getHoldings().toList()

        // Assert
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error)
    }

}