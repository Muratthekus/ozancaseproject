package me.thekusch.ozancaseproject.di

import me.thekusch.ozancaseproject.data.CoinService
import me.thekusch.ozancaseproject.data.impl.CoinRepositoryImpl
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        provideCoinRepository(
            coinService = get()
        )
    }
}

private fun provideCoinRepository(
    coinService: CoinService
): CoinRepository = CoinRepositoryImpl(coinService)