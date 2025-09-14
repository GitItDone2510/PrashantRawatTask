package com.example.prashantrawattask.holdings.data.repository

import android.util.Log
import com.example.prashantrawattask.common.Resource
import com.example.prashantrawattask.holdings.data.getUserHoldingsDetailModel
import com.example.prashantrawattask.holdings.data.local.dao.StockHoldingDao
import com.example.prashantrawattask.holdings.data.remote.StockApi
import com.example.prashantrawattask.holdings.data.remote.dto.Data
import com.example.prashantrawattask.holdings.data.remote.dto.toEntity
import com.example.prashantrawattask.holdings.domain.model.UserHoldingModel
import com.example.prashantrawattask.holdings.domain.model.UserHoldingsDetailModel
import com.example.prashantrawattask.holdings.domain.repositories.HoldingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.collections.map
import kotlin.collections.orEmpty
import kotlin.collections.sumOf

class HoldingRepositoryImpl @Inject constructor(
    private val stockHoldingDao: StockHoldingDao,
    private val api: StockApi
): HoldingRepository {

    override suspend fun getHoldings(): Flow<Resource<UserHoldingsDetailModel>> = flow {
        emit(Resource.Loading())
        try {
            Log.i("MyTag", "In repo getHoldings()")
            val remoteHoldings =
                api.getHoldings("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
            val holdingEntities = remoteHoldings?.userHolding?.map { it.toEntity() }.orEmpty()
            stockHoldingDao.clearAndInsertAll(holdingEntities)
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }

        stockHoldingDao.getAllHoldings().collect { holdings ->
            emit(Resource.Success(getUserHoldingsDetailModel(holdings)))
        }
    }
}