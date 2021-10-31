package com.yoti.android.cryptocurrencychallenge.data

import com.yoti.android.cryptocurrencychallenge.BuildConfig
import com.yoti.android.cryptocurrencychallenge.data.model.assets.AssetsApiData
import com.yoti.android.cryptocurrencychallenge.data.model.markets.MarketsApiData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val CAPCOIN_ENDPOINT_HOST = "https://api.coincap.io/"

interface CoincapService {

    @GET("/v2/assets")
    suspend fun getAssets(
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY
    ): AssetsApiData

    @GET("/v2/markets")
    suspend fun getMarkets(
        @Query("baseId") baseId: String,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
    ): MarketsApiData
}