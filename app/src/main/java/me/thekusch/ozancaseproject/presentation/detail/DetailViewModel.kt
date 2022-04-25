package me.thekusch.ozancaseproject.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.thekusch.ozancaseproject.core.BaseViewModel
import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.usecase.GetCoinDetailUseCase
import me.thekusch.ozancaseproject.domain.usecase.GetCoinPriceHistoryUseCase
import me.thekusch.ozancaseproject.presentation.detail.components.PriceHistoryViewState
import me.thekusch.ozancaseproject.presentation.ext.io
import me.thekusch.ozancaseproject.presentation.ext.result
import me.thekusch.ozancaseproject.util.TimePeriods

class DetailViewModel(
    private val getCoinDetailUseCase: UseCase.RequestUseCase<GetCoinDetailUseCase.Params, DetailViewState>,
    private val getCoinPriceHistoryUseCase: UseCase.RequestUseCase<GetCoinPriceHistoryUseCase.Params, PriceHistoryViewState>
) : BaseViewModel() {

    private val _getCoinDetailLiveData = MutableLiveData<DetailViewState>()

    private val _getCoinPriceHistoryLiveData = MutableLiveData<PriceHistoryViewState>()

    val getCoinDetailLiveData: LiveData<DetailViewState>
        get() = _getCoinDetailLiveData

    val getCoinPriceHistoryLiveData: LiveData<PriceHistoryViewState>
        get() = _getCoinPriceHistoryLiveData

    fun getCoinDetail(
        uuid: String?
    ) = io {
        _getCoinDetailLiveData.result(
            getCoinDetailUseCase.execute(params = GetCoinDetailUseCase.Params(uuid))
        )
    }

    fun getCoinPriceHistory(
        uuid: String?,
        timePeriod: String = TimePeriods.H24.timePeriod
    ) = io {
        _getCoinPriceHistoryLiveData
            .result(
                getCoinPriceHistoryUseCase.execute(
                    params = GetCoinPriceHistoryUseCase.Params(
                        uuid,
                        timePeriod
                    )
                )
            )
    }
}