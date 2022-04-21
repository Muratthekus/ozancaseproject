package me.thekusch.ozancaseproject.core

interface ItemAdapter<T: BaseEntity> {

    var onItemClickListener: ((viewId: String?, T?) -> Unit)?

    fun updateItems(items: List<T>?)

    fun getItemAt(index: Int): T?
}