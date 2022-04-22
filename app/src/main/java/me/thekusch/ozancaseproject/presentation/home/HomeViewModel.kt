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

    var requestOffset: Int = 0
        private set

    private val OFFSET = 10

    private val LIMIT = 10

    var isRequestProcessing = false

    var orderBy: String = FilterType.MARKET_CAP.filter

    private val _getCoinsLiveData = MutableLiveData<HomeViewState>()

    val getCoinsLiveData: LiveData<HomeViewState>
        get() = _getCoinsLiveData

    fun rollBackTheOffset(){
        if(requestOffset<= 0) {
            requestOffset = 0
        } else {
            requestOffset -= OFFSET
        }
    }

    fun clearOffset() {
        requestOffset = 0
    }

    fun setNewOrderBy(pos: Int) {
        orderBy = FilterType.values()[pos].filter
    }

    fun getCoinList() = io {
        isRequestProcessing = true
        _getCoinsLiveData.result(
            getCoinsUseCase.execute(params = GetCoinsUseCase.Params(orderBy,requestOffset,LIMIT))
        )
        requestOffset += OFFSET
    }
}