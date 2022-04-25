package me.thekusch.ozancaseproject.presentation.detail.components

import me.thekusch.ozancaseproject.core.BaseViewState
import me.thekusch.ozancaseproject.util.Status

class PriceHistoryViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CoinDetail.ItemEntity>? = null
): BaseViewState(status,error,data) {
    fun getCoinHistoryResult() = data
}