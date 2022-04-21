package me.thekusch.ozancaseproject.domain.usecase

import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.presentation.home.HomeViewState
import me.thekusch.ozancaseproject.presentation.home.components.CoinList
import me.thekusch.ozancaseproject.util.Status

class GetCoinsUseCase(
    private val coinRepository: CoinRepository
) : UseCase.RequestUseCase<GetCoinsUseCase.Params, HomeViewState> {

    override suspend fun execute(params: Params?): HomeViewState {
        if (params == null) {
            return HomeViewState(Status.ERROR, error = "params can not be null")
        }
        if (params.orderBy.isNullOrEmpty()) {
            return HomeViewState(Status.ERROR, error = "orderBy query can not be null")
        }
        if (params.offSet == null) {
            return HomeViewState(Status.ERROR, error = "offset query can not be null")
        }
        if (params.limit == null) {
            return HomeViewState(Status.ERROR, error = "limit query can not be null")
        }

        val result = coinRepository.getCoins(orderBy = params.orderBy, offset = params.offSet,params.limit)
        return HomeViewState(
            result.status,
            result.message,
            result.data?.data?.coins?.map { coin ->
                CoinList.ItemEntity(
                    coin.change,
                    coin.color,
                    coin.iconUrl,
                    coin.name,
                    coin.price,
                    coin.rank,
                    coin.symbol,
                    coin.uuid
                )
            }
        )
    }

    data class Params(
        val orderBy: String?,
        val offSet: Int?,
        val limit: Int?
    ) : UseCase.Params()

    companion object {
        const val NAME = "GetCoinsUseCase"
    }
}