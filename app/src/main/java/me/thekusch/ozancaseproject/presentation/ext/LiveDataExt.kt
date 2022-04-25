package me.thekusch.ozancaseproject.presentation.ext

import android.os.Looper
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.thekusch.ozancaseproject.core.BaseViewState
import me.thekusch.ozancaseproject.util.Status

inline fun <T : BaseViewState?> LiveData<T>.observeData(
    lifecycleOwner: LifecycleOwner,
    progressBar: View? = null,
    crossinline success: ((T?) -> Unit) = {
        // no-op
    },
    crossinline fail: ((String?) -> Unit) = {
        // no-op
    },
    crossinline loading: (() -> Unit) = {
        //no-op
    },
    crossinline finally: (() -> Unit) = {
        // no-op
    }
) {
    observe(lifecycleOwner, Observer { viewState: T? ->
        when (viewState?.baseStatus) {
            Status.LOADING -> {
                progressBar?.show()
                loading()
            }
            Status.ERROR -> {
                progressBar?.dismiss()
                fail(viewState.baseError)
            }
            Status.SUCCESS -> {
                progressBar?.dismiss()
                success(viewState)
            }
        }
        finally.invoke()
    })
}

fun <T : Any?> LiveData<T?>?.result(result: T?): T? {
    if (this == null) return result
    val isMainThread = Looper.myLooper() == Looper.getMainLooper()
    when (this) {
        is MutableLiveData -> {
            if (isMainThread) {
                this.value = result
            } else {
                this.postValue(result)
            }
        }
        else -> {
            throw IllegalArgumentException("Unsupported LiveData type!")
        }
    }
    return result
}
