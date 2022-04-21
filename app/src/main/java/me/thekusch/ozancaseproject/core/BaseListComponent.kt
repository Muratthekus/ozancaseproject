package me.thekusch.ozancaseproject.core

import androidx.viewbinding.ViewBinding

interface BaseListComponent<VB : ViewBinding, I : BaseEntity, T: BaseEntity>  {

    val binding: VB

    fun setup(items: List<I>?, entity: T? = null)

    fun updateItems(items: List<I>?)
}