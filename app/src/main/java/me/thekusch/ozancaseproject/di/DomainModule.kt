package me.thekusch.ozancaseproject.di

import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.domain.usecase.GetCoinDetailUseCase
import me.thekusch.ozancaseproject.domain.usecase.GetCoinsUseCase
import me.thekusch.ozancaseproject.presentation.detail.DetailViewState
import me.thekusch.ozancaseproject.presentation.detail.components.CoinDetail
import me.thekusch.ozancaseproject.presentation.home.HomeViewState
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory(named(GetCoinsUseCase.NAME)) {
        provideGetCoinsUseCase(
            coinsRepository = get()
        )
    }

    factory(named(GetCoinDetailUseCase.NAME)) {
        provideGetCoinDetailUseCase(
            coinsRepository = get()
        )
    }
}

private fun provideGetCoinsUseCase(
    coinsRepository: CoinRepository
): UseCase.RequestUseCase<GetCoinsUseCase.Params,HomeViewState> =
    GetCoinsUseCase(coinsRepository)

private fun provideGetCoinDetailUseCase(
    coinsRepository: CoinRepository
): UseCase.RequestUseCase<GetCoinDetailUseCase.Params,DetailViewState> =
    GetCoinDetailUseCase(coinsRepository)