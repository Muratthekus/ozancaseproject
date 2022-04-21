package me.thekusch.ozancaseproject.core

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

typealias ItemClickListener<T> = ((viewId: String?, T?) -> Unit)?
abstract class BaseAdapter<T : BaseEntity, VH>(callback: DiffUtil.ItemCallback<T>) :  ItemAdapter<T>,
    ListAdapter<T, VH>(callback) where VH : BaseViewHolder<T> {

    override var onItemClickListener: ItemClickListener<T> = null

    override fun getItemAt(index: Int): T? {
        return currentList[index]
    }

    override fun updateItems(items: List<T>?) {
        items?.let {
            submitList(it)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(currentList[position])
    }


}

