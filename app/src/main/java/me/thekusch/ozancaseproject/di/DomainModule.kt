package me.thekusch.ozancaseproject.di

import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.model.Coin
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.domain.usecase.GetCoinsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory(named(GetCoinsUseCase.NAME)) {
        provideGetCoinsUseCase(
            coinsRepository = get()
        )
    }
}

private fun provideGetCoinsUseCase(
    coinsRepository: CoinRepository
): UseCase.RequestUseCase<GetCoinsUseCase.Params,List<Coin>?> =
    GetCoinsUseCase(coinsRepository)