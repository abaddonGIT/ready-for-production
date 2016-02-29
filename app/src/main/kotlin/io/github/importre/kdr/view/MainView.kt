package io.github.importre.kdr.view

import io.github.importre.kdr.api.repo.Repository
import java.util.*

interface MainView {

    fun showLoading(loading: Boolean)

    fun showError(error: Throwable)

    fun showRepos(repos: ArrayList<Repository>)
}