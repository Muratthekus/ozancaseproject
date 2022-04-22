package me.thekusch.ozancaseproject.presentation.feature

import android.content.Intent
import me.thekusch.ozancaseproject.presentation.ext.createIntentFeature

object DetailPage: Feature<Intent> {

    private val DETAIL_PAGE = "me.thekusch.ozancaseproject.presentation.detail.DetailActivity"

    override val dynamicStart: Intent?
        get() = DETAIL_PAGE.createIntentFeature()
}