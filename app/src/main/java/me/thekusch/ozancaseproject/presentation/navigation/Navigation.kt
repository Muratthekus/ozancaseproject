package me.thekusch.ozancaseproject.presentation.navigation

import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

interface Navigation {

    val activity: WeakReference<FragmentActivity>

    fun close() = activity.get()?.onBackPressed()

    fun finish() = activity.get()?.finish()
}