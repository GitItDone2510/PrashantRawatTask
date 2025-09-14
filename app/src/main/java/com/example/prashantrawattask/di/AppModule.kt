package com.example.prashantrawattask.di

import com.example.prashantrawattask.holdings.data.local.dao.StockHoldingDao
import com.example.prashantrawattask.holdings.data.remote.StockApi
import com.example.prashantrawattask.holdings.data.repository.HoldingRepositoryImpl
import com.example.prashantrawattask.holdings.domain.repositories.HoldingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun okHttpCallFactory(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    },
            )
            .build()


    @Provides
    @Singleton
    fun provideStockApi(
        client: OkHttpClient
    ): StockApi {
        return Retrofit.Builder()
            .baseUrl("https://35dee773a9ec441e9f38d5fc249406ce.api.mockbin.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StockApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHoldingRepository(
        api: StockApi,
        dao: StockHoldingDao
    ): HoldingRepository {
        return HoldingRepositoryImpl(api = api, stockHoldingDao = dao)
    }
}