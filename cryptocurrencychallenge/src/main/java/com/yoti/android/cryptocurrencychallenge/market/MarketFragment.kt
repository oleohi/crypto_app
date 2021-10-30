package com.yoti.android.cryptocurrencychallenge.market

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yoti.android.cryptocurrencychallenge.R
import com.yoti.android.cryptocurrencychallenge.data.API_KEY
import com.yoti.android.cryptocurrencychallenge.data.model.markets.MarketData
import com.yoti.android.cryptocurrencychallenge.databinding.FragmentMarketBinding
import com.yoti.android.cryptocurrencychallenge.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MarketFragment : Fragment() {

    private lateinit var binding: FragmentMarketBinding
    private val viewModel by viewModels<MarketsViewModel>()
    private lateinit var baseId: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = MarketFragmentArgs.fromBundle(requireArguments())
        val currentAssetId = args.assetId

        if (currentAssetId.isNotEmpty()) {
            baseId = currentAssetId
        }

        viewModel.getMarkets(API_KEY, baseId).observe(viewLifecycleOwner) { markets ->
            when (markets) {
                is Resource.Loading -> binding.progressBarMarkets.isVisible = true

                is Resource.Success -> {
                    binding.progressBarMarkets.isVisible = false
                    markets.data?.let {
                        populateWithMarketData(it)
                    }
                }

                is Resource.Error -> {
                    binding.progressBarMarkets.isVisible = false
                    binding.textViewError.isVisible = true
                    binding.textViewError.text = markets.throwable.localizedMessage
                }
                else -> {
                    binding.progressBarMarkets.isVisible = false
                    binding.textViewError.isVisible = true
                    binding.textViewError.text = getString(R.string.error_message)
                }
            }
        }
    }

    private fun populateWithMarketData(data: List<MarketData>) {
        binding.apply {
            textViewExchangeId.text = data.first().exchangeId
            textViewRank.text = data.first().rank
            textViewPrice.text = String.format("%.2f", data.first().priceUsd?.toDouble())
            textViewDate.text = viewModel.formatTime(data.first().updated!!)
        }
    }
}