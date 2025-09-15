package com.example.prashantrawattask.holdings.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockHoldingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockHoldings(stockHoldings: List<UserHoldingEntity>)

    @Query("SELECT * FROM userholdingentity")
    fun getAllHoldings(): Flow<List<UserHoldingEntity>>

    @Query("DELETE FROM userholdingentity")
    suspend fun deleteAllHoldings()

    @Transaction
    suspend fun clearAndInsertAll(holdings: List<UserHoldingEntity>) {
        deleteAllHoldings()
        insertStockHoldings(holdings)
    }
}