package com.yoti.android.cryptocurrencychallenge.data.model.markets

import com.yoti.android.cryptocurrencychallenge.data.CoincapService
import com.yoti.android.cryptocurrencychallenge.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class MarketsRepository @Inject constructor(
    private val api: CoincapService
) {
    suspend fun getMarkets(apiKey: String, baseId: String): Resource<List<MarketData>?> {
        return try {
            val response = api.getMarkets(apiKey, baseId).marketData
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}