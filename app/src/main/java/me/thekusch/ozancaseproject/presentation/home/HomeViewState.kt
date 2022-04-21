package me.thekusch.ozancaseproject.presentation.home

import me.thekusch.ozancaseproject.core.BaseViewState
import me.thekusch.ozancaseproject.domain.model.Coin
import me.thekusch.ozancaseproject.presentation.home.components.CoinList
import me.thekusch.ozancaseproject.util.Status

class HomeViewState(
    val status: Status,
    val error: String? = null,
    val data: List<CoinList.ItemEntity>? = null
): BaseViewState(status,error,data) {
    fun getCoinResult() = data
}