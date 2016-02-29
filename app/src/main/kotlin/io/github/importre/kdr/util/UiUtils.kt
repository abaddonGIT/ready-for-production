package io.github.importre.kdr.util

import android.content.Context
import android.content.res.Configuration

object UiUtils {

    fun isLandscape(context: Context): Boolean {
        val configuration = context.resources.configuration
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}