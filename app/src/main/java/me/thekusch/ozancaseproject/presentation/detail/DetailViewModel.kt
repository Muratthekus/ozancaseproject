package me.thekusch.ozancaseproject.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.thekusch.ozancaseproject.core.BaseViewModel
import me.thekusch.ozancaseproject.core.UseCase
import me.thekusch.ozancaseproject.domain.usecase.GetCoinDetailUseCase
import me.thekusch.ozancaseproject.presentation.ext.io
import me.thekusch.ozancaseproject.presentation.ext.result

class DetailViewModel(
    private val getCoinDetailUseCase: UseCase.RequestUseCase<GetCoinDetailUseCase.Params,DetailViewState>
): BaseViewModel() {

    private val _getCoinDetailLiveData = MutableLiveData<DetailViewState>()

    val getCoinDetailLiveData: LiveData<DetailViewState>
        get() = _getCoinDetailLiveData

    fun getCoinDetail(
        uuid: String?
    ) = io {
        _getCoinDetailLiveData.result(
            getCoinDetailUseCase.execute(params = GetCoinDetailUseCase.Params(uuid))
        )
    }
}