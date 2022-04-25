package me.thekusch.ozancaseproject.presentation.detail.components

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import me.thekusch.ozancaseproject.core.BaseAdapter
import me.thekusch.ozancaseproject.core.BaseViewHolder
import me.thekusch.ozancaseproject.databinding.ItemComponentPriceHistoryBinding
import java.util.*

class CoinPriceHistoryAdapter() :
    BaseAdapter<CoinDetail.ItemEntity, CoinPriceHistoryAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemComponentPriceHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ViewHolder(private val binding: ItemComponentPriceHistoryBinding) :
        BaseViewHolder<CoinDetail.ItemEntity>(binding.root) {

        @SuppressLint("SimpleDateFormat")
        override fun bind(item: CoinDetail.ItemEntity?) {
            super.bind(item)
            item?.let {
                binding.apply {
                    textViewPrice.text = String.format("%,f", it.price.toFloat())
                    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
                    val dateString = formatter.format( Date(it.timeStamp.toLong()*1000L));
                    textViewTimeStamp.text = dateString
                }
            }
        }

    }
}

val diffCallback = object : DiffUtil.ItemCallback<CoinDetail.ItemEntity>() {
    override fun areContentsTheSame(
        oldItem: CoinDetail.ItemEntity,
        newItem: CoinDetail.ItemEntity
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: CoinDetail.ItemEntity,
        newItem: CoinDetail.ItemEntity
    ) = oldItem.timeStamp == newItem.timeStamp
}