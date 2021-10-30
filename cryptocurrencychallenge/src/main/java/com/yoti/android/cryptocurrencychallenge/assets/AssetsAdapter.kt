package com.yoti.android.cryptocurrencychallenge.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoti.android.cryptocurrencychallenge.data.model.assets.AssetData
import com.yoti.android.cryptocurrencychallenge.databinding.AssetItemBinding

class AssetsAdapter(
    private val assetItems: List<AssetData>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AssetsAdapter.AssetItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AssetItemViewHolder(
            AssetItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AssetItemViewHolder, position: Int) {
        holder.bind(assetItems[position])
    }

    override fun getItemCount() = assetItems.size


    interface OnItemClickListener {
        fun onItemClick(asset: AssetData)
    }

    fun getAsset(position: Int): AssetData = assetItems[position]


    inner class AssetItemViewHolder(
        private val binding: AssetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                val asset = getAsset(position)
                listener.onItemClick(asset)
                }
            }
        }

        fun bind(asset: AssetData) {
            binding.textViewAssetCode.text = asset.symbol
            binding.textViewAssetName.text = asset.name
            binding.textViewAssetPrice.text = asset.priceUsd
        }
    }
}

