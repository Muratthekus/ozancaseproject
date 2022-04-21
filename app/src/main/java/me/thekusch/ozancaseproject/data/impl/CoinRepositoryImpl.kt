package me.thekusch.ozancaseproject.data.impl

import me.thekusch.ozancaseproject.core.BaseRepository
import me.thekusch.ozancaseproject.util.Resource
import me.thekusch.ozancaseproject.core.apiCall
import me.thekusch.ozancaseproject.data.CoinService
import me.thekusch.ozancaseproject.domain.model.GetCoinsResponse
import me.thekusch.ozancaseproject.domain.repo.CoinRepository

class CoinRepositoryImpl(
    private val coinService: CoinService
) : BaseRepository(), CoinRepository{

    override suspend fun getCoins(orderBy: String): Resource<GetCoinsResponse?> {
        return apiCall {
            coinService.getCoins(orderBy)
        }
    }
}