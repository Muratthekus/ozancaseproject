package me.thekusch.ozancaseproject.presentation.ext

import android.content.Intent
import me.thekusch.ozancaseproject.BuildConfig


fun String.createIntentFeature() = try {
    Class.forName(this).run {
        Intent(Intent.ACTION_VIEW).setClassName(BuildConfig.APPLICATION_ID, this@createIntentFeature)
    }
} catch (e: ClassNotFoundException) {
    null
}