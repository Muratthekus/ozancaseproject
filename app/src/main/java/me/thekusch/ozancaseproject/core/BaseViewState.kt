package me.thekusch.ozancaseproject.core

import me.thekusch.ozancaseproject.util.Status

open class BaseViewState(val baseStatus: Status, val baseError: String?, val baseData: Any?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}