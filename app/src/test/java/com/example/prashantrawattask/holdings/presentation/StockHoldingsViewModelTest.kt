package com.example.prashantrawattask.holdings.presentation

import com.example.prashantrawattask.MainDispatcherRule
import com.example.prashantrawattask.common.Resource
import com.example.prashantrawattask.holdings.domain.model.UserHoldingModel
import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel
import com.example.prashantrawattask.holdings.domain.repositories.HoldingRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test


@OptIn(ExperimentalCoroutinesApi::class)
class StockHoldingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: HoldingRepository = mockk()
    private lateinit var SUT: StockHoldingsViewModel

    @Before
    fun setup() {
        coEvery { repository.getHoldings() } returns emptyFlow()
    }

    @Test
    fun initialState_defaultState() {
        SUT = StockHoldingsViewModel(repository)

        assert(SUT.state == StockHoldingState())
    }

    @Test
    fun getHoldings_emitsLoading_whenLoading() = runTest {
        // Arrange
        coEvery { repository.getHoldings() } returns flow {
            emit(Resource.Loading())
        }

        // Act
        SUT = StockHoldingsViewModel(repository)

        // Assert
        assert(SUT.state.isLoading)
        assert(SUT.state.error.isEmpty())
        assert(SUT.state.userHoldingsDetails.userHoldings.isEmpty())
    }

    @Test
    fun getHoldings_emitsSuccess_whenSuccess() = runTest {
        // Arrange
        val fakeModel = UserHoldingsDetailModel(
            currentValue = 1000.0,
            totalInvestment = 500.0,
            todayPnl = 200.0,
            totalPnl = 300.0,
            userHoldings = listOf(
                UserHoldingModel(
                    pnL = 200.0,
                    lastTradePrice = 100.0,
                    qty = 10,
                    symbol = "TCS"
                )
            )
        )
        coEvery { repository.getHoldings() } returns flow {
            emit(Resource.Success(fakeModel))
        }

        // Act
        SUT = StockHoldingsViewModel(repository)

        // Assert
        assert(!SUT.state.isLoading)
        assert(SUT.state.error.isEmpty())
        assert(SUT.state.userHoldingsDetails.userHoldings.size == 1)
        assert(SUT.state.userHoldingsDetails.totalPnl == 300.0)
    }

    @Test
    fun getHolding_emitsError_whenError() = runTest {
        // Arrange
        coEvery { repository.getHoldings() } returns flow {
            emit(Resource.Error("Something went wrong"))
        }

        // Act
        SUT = StockHoldingsViewModel(repository)

        // Assert
        assert(!SUT.state.isLoading)
        assert(SUT.state.error == "Something went wrong")
        assert(SUT.state.userHoldingsDetails.userHoldings.isEmpty())
    }
}