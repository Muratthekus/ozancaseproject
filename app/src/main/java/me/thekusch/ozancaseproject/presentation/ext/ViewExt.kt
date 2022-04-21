package me.thekusch.ozancaseproject.presentation.ext

import android.view.View


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.dismiss() {
    this.visibility = View.GONE
}