package me.thekusch.ozancaseproject.core

import me.thekusch.ozancaseproject.util.Resource
import retrofit2.Response

suspend fun <T : Any?> BaseRepository.apiCall(
    apiCall: (suspend () -> T?)
): Resource<T?> {
    return try {
        val apiResult = apiCall.invoke()
        return if (apiResult is Response<*>) {
            if (apiResult.code() >= 400) {
                Resource.Error(
                    "Unexpected error occurred"
                )
            } else {
                Resource.Success(apiResult)
            }
        } else {
            Resource.Success(apiResult)
        }
    } catch (t: Throwable) {
        Resource.Error(
            t.localizedMessage
        )
    }
}