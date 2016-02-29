package io.github.importre.rfp.ext

import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.toast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
