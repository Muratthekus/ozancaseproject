package me.thekusch.ozancaseproject.presentation.feature

import android.content.Intent
import me.thekusch.ozancaseproject.presentation.ext.createIntentFeature

object DetailPage: Feature<Intent> {

    private val DETAIL_PAGE = "me.thekusch.ozancaseproject.presentation.detail.DetailActivity"

    const val BUNDLE_UUID = "BUNDLE_UUID"

    override val dynamicStart: Intent?
        get() = DETAIL_PAGE.createIntentFeature()

    fun dynamicStart(
        uuid: String?
    ) = dynamicStart?.apply {
        putExtra(BUNDLE_UUID,uuid)
    }
}