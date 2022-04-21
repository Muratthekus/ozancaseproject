package me.thekusch.ozancaseproject.di

import me.thekusch.ozancaseproject.domain.usecase.GetCoinsUseCase
import me.thekusch.ozancaseproject.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(
            getCoinsUseCase = get(named(GetCoinsUseCase.NAME))
        )
    }
}