package me.thekusch.ozancaseproject.core

import me.thekusch.ozancaseproject.util.Status

sealed class Resource<T>(val status: Status,val data: T? = null, val message: String? = null) {

    class Success<T>(data: T?) : Resource<T>(Status.SUCCESS,data)

    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(Status.ERROR,data, message)

    class Loading<T>(data: T? = null) : Resource<T>(Status.LOADING,data)
}