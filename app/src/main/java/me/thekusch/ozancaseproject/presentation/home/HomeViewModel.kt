package me.thekusch.ozancaseproject.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.thekusch.ozancaseproject.core.BaseViewModel
import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.usecase.GetCoinsUseCase
import me.thekusch.ozancaseproject.presentation.ext.io
import me.thekusch.ozancaseproject.presentation.ext.result
import me.thekusch.ozancaseproject.util.FilterType

class HomeViewModel(
    private val getCoinsUseCase: UseCase.RequestUseCase<GetCoinsUseCase.Params,HomeViewState>
): BaseViewModel() {

    private val _getCoinsLiveData = MutableLiveData<HomeViewState>()

    val getCoinsLiveData: LiveData<HomeViewState>
        get() = _getCoinsLiveData

    fun getCoinList(
        orderBy: String = FilterType.MARKET_CAP.filter
    ) = io {
        _getCoinsLiveData.result(
            getCoinsUseCase.execute(params = GetCoinsUseCase.Params(orderBy))
        )
    }
}