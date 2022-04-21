package me.thekusch.ozancaseproject.presentation.ext


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun ViewModel.io(codeBlock: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            codeBlock()
        }
    }

}