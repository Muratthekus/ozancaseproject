package me.thekusch.ozancaseproject.domain.repo

import me.thekusch.ozancaseproject.core.Resource
import me.thekusch.ozancaseproject.domain.model.GetCoinsResponse

interface CoinRepository {

    suspend fun getCoins(orderBy: String): Resource<GetCoinsResponse?>
}