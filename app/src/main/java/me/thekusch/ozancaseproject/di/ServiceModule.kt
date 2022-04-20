package me.thekusch.ozancaseproject.di

import me.thekusch.ozancaseproject.data.CoinService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
        provideAccountService(retrofit = get())
    }
}

private fun provideAccountService(retrofit: Retrofit): CoinService
        = retrofit.create(CoinService::class.java)