package me.thekusch.ozancaseproject.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.domain.usecase.GetCoinDetailUseCase
import me.thekusch.ozancaseproject.util.Resource
import me.thekusch.ozancaseproject.util.Status
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCoinDetailUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var coinRepository: CoinRepository

    private lateinit var getCoinsUseCase: GetCoinDetailUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getCoinsUseCase = GetCoinDetailUseCase(coinRepository)
    }

    @Test
    fun `get coin detail with invalid uuid returns fail`() = runBlockingTest {
        val uuid = ""
        coEvery {
            coinRepository.getCoinDetail(uuid)
        } returns Resource.Success(data = null)

        val result = getCoinsUseCase.execute(
            params = GetCoinDetailUseCase.Params(
                uuid
            )
        )
        Assert.assertTrue(result.status == Status.ERROR)
    }

    @Test
    fun `get coins detail with valid uuid returns success`() = runBlockingTest {
        val uuid = "test"
        coEvery {
            coinRepository.getCoinDetail(uuid)
        } returns Resource.Success(data = null)

        val result = getCoinsUseCase.execute(
            params = GetCoinDetailUseCase.Params(
                uuid
            )
        )
        Assert.assertTrue(result.status == Status.SUCCESS)
    }
}