package me.thekusch.ozancaseproject.presentation.detail.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import me.thekusch.ozancaseproject.R
import me.thekusch.ozancaseproject.core.BaseComponent
import me.thekusch.ozancaseproject.core.BaseEntity
import me.thekusch.ozancaseproject.databinding.ComponentCoinDetailBinding
import java.lang.Exception

class CoinDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.coinDetailStyle
): FrameLayout(context, attrs, defStyleAttr), BaseComponent<ComponentCoinDetailBinding, CoinDetail.ItemEntity> {

    override val binding: ComponentCoinDetailBinding =
        ComponentCoinDetailBinding.inflate(
            LayoutInflater.from(context),
            this,true
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

    override fun setup(viewEntity: ItemEntity?) {
        TODO("Not yet implemented")
    }

    data class ItemEntity(
        val allTimeHighPrice: String,
        val allTimeHighTimestamp: Int,
        val change: String?,
        val color: String?,
        val description: String?,
        val iconUrl: String?,
        val name: String?,
        val price: String?,
        val sparkline: List<String>,
        val symbol: String?,
        val uuid: String
    ): BaseEntity()

}