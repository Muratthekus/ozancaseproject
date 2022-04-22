package me.thekusch.ozancaseproject.presentation.home.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import me.thekusch.ozancaseproject.R
import me.thekusch.ozancaseproject.core.BaseEntity
import me.thekusch.ozancaseproject.core.BaseListComponent
import me.thekusch.ozancaseproject.databinding.ComponentCoinListBinding
import java.lang.Exception

typealias SortSelectListener = ((pos: Int) -> Unit)
class CoinList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.coinListStyle
): FrameLayout(context, attrs, defStyleAttr),
BaseListComponent<ComponentCoinListBinding,CoinList.ItemEntity,CoinList.Entity>,
    AdapterView.OnItemSelectedListener{

    var itemAdapter = CoinListAdapter()
        private set

    // Attribute Defaults
    @ColorInt
    private var _backgroundColor = Color.TRANSPARENT

    // Core Attributes
    var listBackgroundColor: Int
        @ColorInt get() = _backgroundColor
        set(@ColorInt value) {
            _backgroundColor = value
            setBackgroundColor(value)
        }

    var titles: CharSequence?
        get() = binding.textViewTitle.text
        set(value) {
            binding.textViewTitle.text = value
        }

    // Listeners
    var onItemClickListener
        get() = itemAdapter.onItemClickListener
        set(value) {
            itemAdapter.onItemClickListener = value
        }

    var onSortSelectListener: SortSelectListener? = null

    override val binding: ComponentCoinListBinding =
        ComponentCoinListBinding.inflate(
            LayoutInflater.from(context),
            this,true
        )

    init {
        obtainStyledAttributes(attrs, defStyleAttr)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        ArrayAdapter.createFromResource(
            context,
            R.array.sortList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerSortList.adapter = adapter
        }
        binding.spinnerSortList.onItemSelectedListener = this
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CoinList,
            defStyleAttr,
            0
        )
        try {
            with(typedArray) {
                listBackgroundColor = getColor(
                    R.styleable.CoinList_listBackgroundColor,
                    listBackgroundColor
                )
                titles = getString(
                    R.styleable.CoinList_listName
                )
            }
        } catch (e: Exception) {
            // ignored
        } finally {
            typedArray.recycle()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        onSortSelectListener?.invoke(p2)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // no-op
    }

    override fun setup(items: List<ItemEntity>?, entity: Entity?) {
        updateItems(items)
    }

    override fun updateItems(items: List<ItemEntity>?) {
        val oldList = itemAdapter.currentList.toMutableList()
        items?.let {
            oldList.addAll(it)
        }
        itemAdapter.updateItems(oldList)
    }

    fun clearAll() {
        itemAdapter.clearAll()
    }

    class Entity: BaseEntity()

    data class ItemEntity(
        val change: String?,
        val color: String?,
        val iconUrl: String?,
        val name: String?,
        val price: String?,
        val rank: Int?,
        val symbol: String?,
        val uuid: String
    ): BaseEntity()

}