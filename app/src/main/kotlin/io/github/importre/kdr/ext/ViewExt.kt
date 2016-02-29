package io.github.importre.kdr.ext

import android.support.annotation.IdRes
import android.view.View
import android.widget.ImageView
import android.widget.TextView

fun View.getTagAsTextView(@IdRes id: Int): TextView = getTag(id) as TextView

fun View.getTagAsImageView(@IdRes id: Int): ImageView = getTag(id) as ImageView
