package com.example.prashantrawattask.holdings.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.prashantrawattask.holdings.data.local.dao.StockHoldingDao
import com.example.prashantrawattask.holdings.data.local.model.UserHoldingEntity

@Database(
    entities = [UserHoldingEntity::class],
    version = 1
)
abstract class StocksDatabase: RoomDatabase() {
    abstract fun holdingDao(): StockHoldingDao
}