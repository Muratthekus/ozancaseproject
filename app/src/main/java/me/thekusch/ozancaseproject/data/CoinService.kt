package me.thekusch.ozancaseproject.data

import me.thekusch.ozancaseproject.domain.model.GetCoinsResponse
import me.thekusch.ozancaseproject.domain.model.coinDetail.GetCoinDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinService {

    @GET("${Config.API_VERSION}/coins")
    suspend fun getCoins(
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): GetCoinsResponse?

    @GET("${Config.API_VERSION}/coin/{uuid}")
    suspend fun getCoinDetail(
        @Path("uuid") uuid: String
    ): GetCoinDetailResponse?
}