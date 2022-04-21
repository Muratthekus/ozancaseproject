package me.thekusch.ozancaseproject.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T: BaseEntity>(itemView: View): RecyclerView.ViewHolder(itemView) {

    protected var boundItem: T? = null

    open fun bind(item: T?) {
        boundItem = item
    }

}