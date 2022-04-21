package me.thekusch.ozancaseproject.domain.usecase

import me.thekusch.ozancaseproject.core.Resource
import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.model.Coin
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.presentation.home.HomeViewState
import me.thekusch.ozancaseproject.util.Status

class GetCoinsUseCase(
    private val coinRepository: CoinRepository
): UseCase.RequestUseCase<GetCoinsUseCase.Params,HomeViewState> {

    override suspend fun execute(params: Params?): HomeViewState{
        if(params == null) {
            return HomeViewState(Status.ERROR, error = "params can not be null")
        }
        if(params.orderBy.isNullOrEmpty()) {
            return HomeViewState(Status.ERROR, error = "orderBy query can not be null")
        }

        val result = coinRepository.getCoins(orderBy = params.orderBy)
        return HomeViewState(
            result.status,
            result.message,
            result.data?.data?.coins
        )
    }

    data class Params(
        val orderBy: String?
    ): UseCase.Params()

    companion object {
        const val NAME = "GetCoinsUseCase"
    }
}