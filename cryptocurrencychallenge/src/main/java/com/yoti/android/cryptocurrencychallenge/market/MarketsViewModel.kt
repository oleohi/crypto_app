package com.yoti.android.cryptocurrencychallenge.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yoti.android.cryptocurrencychallenge.data.model.markets.MarketsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MarketsViewModel @Inject constructor(
    private val repository: MarketsRepository
) : ViewModel() {

    fun getMarkets(apiKey: String, baseId: String) = liveData(Dispatchers.IO) {
        emit(repository.getMarkets(apiKey, baseId))
    }

    fun formatTime(timestamp: Long): String {
        val date = Date(timestamp)
        val formatted = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatted.format(date)
    }
}