package com.yoti.android.cryptocurrencychallenge.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yoti.android.cryptocurrencychallenge.data.model.markets.MarketsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MarketsViewModel @Inject constructor(
    private val repository: MarketsRepository
) : ViewModel() {

    fun getMarkets(apiKey: String, baseId: String) = liveData(Dispatchers.IO) {
        emit(repository.getMarkets(apiKey, baseId))
    }
}