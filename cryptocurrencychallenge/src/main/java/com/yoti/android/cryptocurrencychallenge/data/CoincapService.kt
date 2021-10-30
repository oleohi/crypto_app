package com.yoti.android.cryptocurrencychallenge.data

import com.yoti.android.cryptocurrencychallenge.data.model.assets.AssetsApiData
import com.yoti.android.cryptocurrencychallenge.data.model.markets.MarketsApiData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val CAPCOIN_ENDPOINT_HOST = "https://api.coincap.io/"
const val API_KEY = "65b0b757-4317-4fd2-8ecc-e5c9e29ae138"

interface CoincapService {

    @GET("/v2/assets")
    suspend fun getAssets(
        @Header("Authorization") apiKey: String
    ): AssetsApiData

    @GET("/v2/markets")
    suspend fun getMarkets(
        @Header("Authorization") apiKey: String,
        @Query("baseId") baseId: String
    ): MarketsApiData
}