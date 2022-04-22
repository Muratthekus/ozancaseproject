package me.thekusch.ozancaseproject.presentation.navigation

import androidx.fragment.app.FragmentActivity
import me.thekusch.ozancaseproject.presentation.feature.DetailPage
import java.lang.ref.WeakReference

interface Navigation {

    val activity: WeakReference<FragmentActivity>

    fun close() = activity.get()?.onBackPressed()

    fun finish() = activity.get()?.finish()

    fun navigateToDetailPage() {
        activity.get()?.startActivity(DetailPage.dynamicStart)
    }
}