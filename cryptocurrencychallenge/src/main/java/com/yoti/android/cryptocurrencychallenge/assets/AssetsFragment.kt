package com.yoti.android.cryptocurrencychallenge.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yoti.android.cryptocurrencychallenge.R
import com.yoti.android.cryptocurrencychallenge.data.model.assets.AssetData
import com.yoti.android.cryptocurrencychallenge.databinding.FragmentAssetsBinding
import com.yoti.android.cryptocurrencychallenge.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AssetsFragment : Fragment(), AssetsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentAssetsBinding
    private val viewModel by viewModels<AssetsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAssetsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getAssets()
        collectUiEvents()
    }

    private fun initRecyclerView() {
        binding.recyclerViewAssets.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerViewAssets.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getAssets() {

        viewModel.getAssets().observe(viewLifecycleOwner) { assets ->
            when (assets) {
                is Resource.Loading -> binding.progressBarAssets.isVisible = true

                is Resource.Success ->
                {
                    binding.progressBarAssets.isVisible = false
                    val adapter = AssetsAdapter(assets.data ?: emptyList(), this)
                    binding.recyclerViewAssets.adapter = adapter
                }

                is Error -> {
                    binding.progressBarAssets.isVisible = false
                    binding.textViewError.isVisible = true
                    binding.textViewError.text = assets.localizedMessage
                }
                else -> {
                    binding.progressBarAssets.isVisible = false
                    binding.textViewError.isVisible = true
                    binding.textViewError.text = getString(R.string.error_message)
                }
            }
        }
    }

    private fun collectUiEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.assetEvent.collect { event ->
                when (event) {
                    is AssetsViewModel.AssetEvent.NavigateToMarketFragment -> {
                        val action = AssetsFragmentDirections.actionAssetsFragmentToMarketFragment(event.asset.id?:"")
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    override fun onItemClick(asset: AssetData) {
        viewModel.onAssetSelected(asset)
    }
}