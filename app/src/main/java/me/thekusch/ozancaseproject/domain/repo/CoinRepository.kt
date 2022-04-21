package me.thekusch.ozancaseproject.domain.repo

import me.thekusch.ozancaseproject.util.Resource
import me.thekusch.ozancaseproject.domain.model.GetCoinsResponse

interface CoinRepository {

    suspend fun getCoins(
        orderBy: String,
        offset: Int,
        limit: Int
    ): Resource<GetCoinsResponse?>
}