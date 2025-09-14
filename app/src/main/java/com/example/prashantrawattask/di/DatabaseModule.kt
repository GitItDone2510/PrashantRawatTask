package com.example.prashantrawattask.di

import android.content.Context
import androidx.room.Room
import com.example.prashantrawattask.holdings.data.local.StocksDatabase
import com.example.prashantrawattask.holdings.data.local.dao.StockHoldingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): StocksDatabase = Room.databaseBuilder(
        context,
        StocksDatabase::class.java,
        "stocks-database",
    ).build()

    @Provides
    fun providesTopicsDao(
        database: StocksDatabase,
    ): StockHoldingDao = database.holdingDao()
}