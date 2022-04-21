package me.thekusch.ozancaseproject.domain.usecase

import me.thekusch.ozancaseproject.core.Resource
import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.model.Coin
import me.thekusch.ozancaseproject.domain.repo.CoinRepository

class GetCoinsUseCase(
    private val coinRepository: CoinRepository
): UseCase.RequestUseCase<GetCoinsUseCase.Params,List<Coin>?> {

    override suspend fun execute(params: Params?): Resource<List<Coin>?> {
        if(params == null) {
            return Resource.Error(message = "Params can not be null")
        }
        if(params.orderBy.isNullOrEmpty()) {
            return Resource.Error(message = "orderBy query can not be null")
        }

        return when(val result = coinRepository.getCoins(orderBy = params.orderBy)) {
            is Resource.Success -> {
                Resource.Success(data = result.data?.data?.coins)
            }
            is Resource.Error -> {
                Resource.Error(result.message)
            }
            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }

    data class Params(
        val orderBy: String?
    ): UseCase.Params()

    companion object {
        const val NAME = "GetCoinsUseCase"
    }
}