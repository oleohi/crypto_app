package com.yoti.android.cryptocurrencychallenge.data.model.assets

import com.yoti.android.cryptocurrencychallenge.data.CoincapService
import com.yoti.android.cryptocurrencychallenge.utils.Resource
import javax.inject.Inject

class AssetsRepository @Inject constructor(
    private val assetsApi: CoincapService
) {

    suspend fun getAssets(): Resource<List<AssetData>?> {
        return try {
            val response = assetsApi.getAssets().assetData
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}