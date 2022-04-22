package me.thekusch.ozancaseproject.core

import androidx.viewbinding.ViewBinding

interface BaseComponent<VB: ViewBinding,T : BaseEntity> {

    val binding: VB

    fun setup(viewEntity: T?)
}
