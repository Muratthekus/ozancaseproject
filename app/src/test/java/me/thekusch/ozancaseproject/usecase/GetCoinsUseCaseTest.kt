package me.thekusch.ozancaseproject.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import me.thekusch.ozancaseproject.domain.repo.CoinRepository
import me.thekusch.ozancaseproject.domain.usecase.GetCoinsUseCase
import me.thekusch.ozancaseproject.util.Resource
import me.thekusch.ozancaseproject.util.Status
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCoinsUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var coinRepository: CoinRepository

    private lateinit var getCoinsUseCase: GetCoinsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getCoinsUseCase = GetCoinsUseCase(coinRepository)
    }

    @Test
    fun `get coins with invalid query returns fail`() = runBlockingTest {
        val query = ""
        val offset = 10
        val limit = 10
        coEvery {
            coinRepository.getCoins(query,offset,limit)
        } returns Resource.Success(data = null)

        val result = getCoinsUseCase.execute(
            params = GetCoinsUseCase.Params(
                query,offset,limit
            )
        )
        Assert.assertTrue(result.status == Status.ERROR)
    }

    @Test
    fun `get coins with valid query returns success`() = runBlockingTest {
        val query = "marketCap"
        val offset = 10
        val limit = 10
        coEvery {
            coinRepository.getCoins(query,offset,limit)
        } returns Resource.Success(data = null)

        val result = getCoinsUseCase.execute(
            params = GetCoinsUseCase.Params(
                query,offset,limit
            )
        )
        Assert.assertTrue(result.status == Status.SUCCESS)
    }
}