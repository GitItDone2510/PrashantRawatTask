package com.example.prashantrawattask

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.prashantrawattask.holdings.data.local.StocksDatabase
import com.example.prashantrawattask.holdings.data.local.dao.StockHoldingDao
import org.junit.After
import org.junit.Before

abstract class DatabaseTest {

    private lateinit var db: StocksDatabase
    protected lateinit var stockHoldingDao: StockHoldingDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                StocksDatabase::class.java,
            ).build()
        }
        stockHoldingDao = db.holdingDao()
    }

    @After
    fun teardown() = db.close()

}