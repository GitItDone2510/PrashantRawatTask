package com.example.prashantrawattask.holdings.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockHoldingDao {

    @Upsert
    suspend fun upsertStockHoldings(stockHoldings: List<UserHoldingEntity>)

    @Query("SELECT * FROM userholdingentity")
    suspend fun getAllHoldings(): Flow<List<UserHoldingEntity>>

    @Query("DELETE FROM userholdingentity")
    suspend fun deleteAllHoldings()
}