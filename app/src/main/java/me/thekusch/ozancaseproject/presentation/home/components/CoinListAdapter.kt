package me.thekusch.ozancaseproject.presentation.home.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import me.thekusch.ozancaseproject.R
import me.thekusch.ozancaseproject.core.BaseAdapter
import me.thekusch.ozancaseproject.core.BaseViewHolder
import me.thekusch.ozancaseproject.databinding.ItemComponentCoinListBinding

class CoinListAdapter() :
    BaseAdapter<CoinList.ItemEntity, CoinListAdapter.ItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemComponentCoinListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class ItemViewHolder(private val binding: ItemComponentCoinListBinding) :
        BaseViewHolder<CoinList.ItemEntity>(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(boundItem?.uuid, boundItem)
            }
        }

        override fun bind(item: CoinList.ItemEntity?) {
            super.bind(item)
            item?.let {
                binding.apply {
                    textViewCoinName.text = it.name
                    textViewCoinNickName.text = it.symbol
                    textViewCoinPrice.text = String.format("%,f", it.price?.toFloat())
                    textViewCoinChange.text = it.change

                    "${it.change}%".also { textViewCoinChange.text = it }
                    if (it.change?.contains("-") == true) {
                        textViewCoinChange.setTextColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.red
                            )
                        )
                    }
                    else{
                        textViewCoinChange.setTextColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.green
                            )
                        )
                    }

                    Glide.with(itemView.context)
                        .load(it.iconUrl)
                        .placeholder(R.drawable.ic_baseline_error_outline_24)
                        .into(imageViewIcon)
                }
            }
        }
    }
    fun clearAll() {
        submitList(null)
    }
}

val diffCallback = object : DiffUtil.ItemCallback<CoinList.ItemEntity>() {
    override fun areContentsTheSame(
        oldItem: CoinList.ItemEntity,
        newItem: CoinList.ItemEntity
    ): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(
        oldItem: CoinList.ItemEntity,
        newItem: CoinList.ItemEntity
    ): Boolean =
        oldItem.uuid == newItem.uuid
}