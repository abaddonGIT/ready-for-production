package io.github.importre.rfp.view

import io.github.importre.rfp.api.repo.Repository
import java.util.*

interface MainView {

    fun showLoading(loading: Boolean)

    fun showError(error: Throwable)

    fun showRepos(repositories: ArrayList<Repository>)
}