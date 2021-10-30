package com.yoti.android.cryptocurrencychallenge.assets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.yoti.android.cryptocurrencychallenge.data.model.assets.AssetData
import com.yoti.android.cryptocurrencychallenge.data.model.assets.AssetsRepository
import com.yoti.android.cryptocurrencychallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetsViewModel @Inject constructor(
    private val repository: AssetsRepository
) : ViewModel() {

    fun getAssets(apiKey: String) = liveData(Dispatchers.IO) {
//        emit(Resource.Loading())
        emit(repository.getAssets(apiKey))
    }

    private val assetEventChannel = Channel<AssetEvent>()
    val assetEvent = assetEventChannel.receiveAsFlow()

    fun onAssetSelected(asset: AssetData) {
        viewModelScope.launch {
            assetEventChannel.send(AssetEvent.NavigateToMarketFragment(asset))
        }
    }

    sealed class AssetEvent{
        data class NavigateToMarketFragment(val asset: AssetData): AssetEvent()
    }
}