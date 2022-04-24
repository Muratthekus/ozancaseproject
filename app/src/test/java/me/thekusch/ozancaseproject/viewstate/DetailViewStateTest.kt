package me.thekusch.ozancaseproject.viewstate

import com.google.common.truth.Truth
import me.thekusch.ozancaseproject.presentation.detail.DetailViewState
import me.thekusch.ozancaseproject.util.Status
import org.junit.Test

class DetailViewStateTest {

    @Test
    fun `should return loading true when status is loading`() {
        // Given
        val givenViewState = DetailViewState(status = Status.LOADING)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }

    @Test
    fun `should not return loading false when status is error`() {
        // Given
        val givenViewState = DetailViewState(status = Status.SUCCESS)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should not return loading false when status is success`() {
        // Given
        val givenViewState = DetailViewState(status = Status.ERROR)

        // When
        val actualResult = givenViewState.isLoading()

        // Then
        Truth.assertThat(actualResult).isFalse()
    }

    @Test
    fun `should return correct error message when status is error`() {
        // Given
        val givenViewState =
            DetailViewState(
                status = Status.ERROR,
                error = "Unexpected error occurred"
            )

        // When
        val actualResult = givenViewState.getErrorMessage()

        // Then
        Truth.assertThat(actualResult).isEqualTo("Unexpected error occurred")
    }

    @Test
    fun `should return true for error placeholder visibility  when status is error`() {
        // Given
        val givenViewState =
            DetailViewState(
                status = Status.ERROR,
                error = "Unexpected error occurred"
            )

        // When
        val actualResult = givenViewState.shouldShowErrorMessage()

        // Then
        Truth.assertThat(actualResult).isTrue()
    }
}