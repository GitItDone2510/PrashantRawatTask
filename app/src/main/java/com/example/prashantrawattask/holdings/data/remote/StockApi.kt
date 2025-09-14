package com.example.prashantrawattask.holdings.data.remote

import com.example.prashantrawattask.holdings.data.remote.dto.StockResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface StockApi {

    @GET()
    suspend fun getHoldings(@Url url: String): StockResponse?
}