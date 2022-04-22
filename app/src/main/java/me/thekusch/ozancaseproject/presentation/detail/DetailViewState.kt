package me.thekusch.ozancaseproject.presentation.detail

import me.thekusch.ozancaseproject.core.BaseViewState
import me.thekusch.ozancaseproject.presentation.detail.components.CoinDetail
import me.thekusch.ozancaseproject.util.Status

class DetailViewState(
    val status: Status,
    val error: String? = null,
    val data: CoinDetail.ItemEntity? = null
): BaseViewState(status,error,data) {
    fun getCoinDetailResult() = data
}