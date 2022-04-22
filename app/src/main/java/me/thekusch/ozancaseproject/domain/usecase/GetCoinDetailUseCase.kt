package me.thekusch.ozancaseproject.domain.usecase

import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.presentation.detail.DetailViewState
import me.thekusch.ozancaseproject.presentation.detail.components.CoinDetail
import me.thekusch.ozancaseproject.util.Status

class GetCoinDetailUseCase(
    private val coinRepository: CoinRepository
) : UseCase.RequestUseCase<GetCoinDetailUseCase.Params,DetailViewState> {

    override suspend fun execute(params: Params?): DetailViewState {
        if(params == null) {
            return DetailViewState(Status.ERROR, error = "Params can not be null")
        }
        if(params.uuid.isNullOrEmpty()) {
            return DetailViewState(Status.ERROR, error = "UUID can not be null")
        }
        val result = coinRepository.getCoinDetail(params.uuid)
        return DetailViewState(
            result.status,
            result.message,
            result.data?.data?.coin?.run {
                CoinDetail.ItemEntity(
                    change = change,
                    color = color,
                    iconUrl = iconUrl,
                    name = name,
                    price = price,
                    sparkline = sparkline,
                    symbol = symbol,
                    uuid = uuid,
                    allTimeHighPrice = this.allTimeHigh.price,
                    allTimeHighTimestamp = this.allTimeHigh.timestamp
                )
            }

        )
    }

    data class Params(
        val uuid: String?
    ): UseCase.Params()

    companion object {
        const val NAME = "GetCoinDetailUseCase"
    }
}