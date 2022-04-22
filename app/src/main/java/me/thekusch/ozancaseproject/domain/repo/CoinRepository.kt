package me.thekusch.ozancaseproject.domain.repo

import me.thekusch.ozancaseproject.util.Resource
import me.thekusch.ozancaseproject.domain.model.GetCoinsResponse
import me.thekusch.ozancaseproject.domain.model.coinDetail.GetCoinDetailResponse

interface CoinRepository {

    suspend fun getCoins(
        orderBy: String,
        offset: Int,
        limit: Int
    ): Resource<GetCoinsResponse?>

    suspend fun getCoinDetail(
        uuid: String
    ): Resource<GetCoinDetailResponse?>
}