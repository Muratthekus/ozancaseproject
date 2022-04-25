package me.thekusch.ozancaseproject.presentation.detail.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import me.thekusch.ozancaseproject.R
import me.thekusch.ozancaseproject.core.BaseComponent
import me.thekusch.ozancaseproject.core.BaseEntity
import me.thekusch.ozancaseproject.core.BaseListComponent
import me.thekusch.ozancaseproject.databinding.ComponentCoinDetailBinding
import java.lang.Exception

class CoinDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.coinDetailStyle
) : FrameLayout(context, attrs, defStyleAttr),
    BaseListComponent<ComponentCoinDetailBinding, CoinDetail.ItemEntity,CoinDetail.Entity> {

    var itemAdapter = CoinPriceHistoryAdapter()
        private set

    override val binding: ComponentCoinDetailBinding =
        ComponentCoinDetailBinding.inflate(
            LayoutInflater.from(context),
            this, true
        )

    // Attribute Defaults
    @ColorInt
    private var _backgroundColor = Color.TRANSPARENT

    // Core Attributes
    var componentBackgroundColor: Int
        @ColorInt get() = _backgroundColor
        set(@ColorInt value) {
            _backgroundColor = value
            setBackgroundColor(value)
        }

    init {
        obtainStyledAttributes(attrs, defStyleAttr)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CoinDetail,
            defStyleAttr,
            0
        )
        try {
            with(typedArray) {
                componentBackgroundColor = getColor(
                    R.styleable.CoinDetail_componentBackgroundColor,
                    componentBackgroundColor
                )
            }
        } catch (e: Exception) {
            // ignored
        } finally {
            typedArray.recycle()
        }
    }

    private fun getSparkLinesAsFloat(list: List<String>?): List<Float>? {
        return list?.map {
            it.toFloat()
        }
    }

    override fun setup(items: List<ItemEntity>?, entity: Entity?) {
        entity?.let { entity ->
            binding.apply {
                val sparkList = getSparkLinesAsFloat(entity.sparkline)
                textViewCoinName.text = entity.name
                "$${String.format("%,f", entity.price?.toFloat())}".also {
                    textViewCoinPrice.text = it
                }
                textViewHigh.text = String.format("%,f", sparkList?.maxOrNull() ?: 0F)
                textViewHigh.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.green
                    )
                )
                textViewLow.text = String.format("%,f", sparkList?.minOrNull() ?: 0F)
                textViewLow.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.red
                    )
                )
                "${entity.change}%".also { textViewChange.text = it }
                if (entity.change?.contains("-") == true) {
                    textViewChange.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.red
                        )
                    )
                }
                if (entity.change?.contains("+") == true) {
                    textViewChange.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.green
                        )
                    )
                }
                Glide.with(context)
                    .load(entity.iconUrl)
                    .placeholder(R.drawable.ic_baseline_error_outline_24)
                    .into(imageViewIcon)
            }
        }
        updateItems(items)
    }

    override fun updateItems(items: List<ItemEntity>?) {
        items?.let {
            itemAdapter.updateItems(items)
        }
    }

    data class Entity(
        val change: String?,
        val color: String?,
        val iconUrl: String?,
        val name: String?,
        val price: String?,
        val sparkline: List<String>?,
        val symbol: String?,
        val uuid: String
    ): BaseEntity()

    data class ItemEntity(
        val price: String,
        val timeStamp: Int,
    ) : BaseEntity()

}