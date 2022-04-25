package me.thekusch.ozancaseproject.domain.usecase

import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.presentation.detail.components.CoinDetail
import me.thekusch.ozancaseproject.presentation.detail.components.PriceHistoryViewState
import me.thekusch.ozancaseproject.util.Status

class GetCoinPriceHistoryUseCase(
    private val coinRepository: CoinRepository
) : UseCase.RequestUseCase<GetCoinPriceHistoryUseCase.Params, PriceHistoryViewState> {

    override suspend fun execute(params: Params?): PriceHistoryViewState {
        if (params == null) {
            return PriceHistoryViewState(Status.ERROR, error = "params can not be null")
        }
        if (params.uuid.isNullOrEmpty()) {
            return PriceHistoryViewState(Status.ERROR, error = "UUID query can not be null")
        }
        if (params.timePeriod == null) {
            return PriceHistoryViewState(Status.ERROR, error = "Time period query can not be null")
        }
        val result = coinRepository.getCoinPriceResponse(params.uuid, params.timePeriod)
        return PriceHistoryViewState(
            status = result.status,
            error = result.message,
            data = result.data?.data?.history?.map {
                CoinDetail.ItemEntity(
                    it.price,
                    it.timestamp
                )
            }
        )
    }

    data class Params(
        val uuid: String?,
        val timePeriod: String?
    ) : UseCase.Params()

    companion object {
        const val NAME = "GetCoinPriceHistoryUseCase"
    }
}