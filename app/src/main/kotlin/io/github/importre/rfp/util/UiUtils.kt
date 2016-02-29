package io.github.importre.rfp.util

import android.content.Context
import android.content.res.Configuration

object UiUtils {

    fun isLandscape(context: Context): Boolean {
        val configuration = context.resources.configuration
        return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}