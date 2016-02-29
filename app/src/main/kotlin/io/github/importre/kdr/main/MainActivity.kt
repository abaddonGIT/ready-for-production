package io.github.importre.kdr.main

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import io.github.importre.kdr.BuildConfig
import io.github.importre.kdr.R
import io.github.importre.kdr.api.repo.Repository
import io.github.importre.kdr.app.App
import io.github.importre.kdr.ext.toast
import io.github.importre.kdr.presenter.MainPresenter
import io.github.importre.kdr.util.UiUtils
import io.github.importre.kdr.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    private val user = BuildConfig.GITHUB_USER
    private val adapter by lazy { RepoAdapter(this) }
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        initUi()

        presenter.setView(this)
        presenter.loadRepos(user, savedInstanceState == null)
    }

    override fun onDestroy() {
        presenter.stop()
        super.onDestroy()
    }

    private fun inject() {
        (application as App).appComp.inject(this)
    }

    private fun initUi() {
        setSupportActionBar(toolbar)
        initRecyclerView()
        initSwipeRefreshView()
    }

    private fun initRecyclerView() {
        val span = if (UiUtils.isLandscape(this)) 2 else 1
        val layoutManager = StaggeredGridLayoutManager(span, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun initSwipeRefreshView() {
        swipeView.setOnRefreshListener { presenter.loadRepos(user, true) }
        swipeView.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.color01),
                ContextCompat.getColor(this, R.color.color02),
                ContextCompat.getColor(this, R.color.color03),
                ContextCompat.getColor(this, R.color.color04))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading(loading: Boolean) {
        handler.postDelayed({
            swipeView.isRefreshing = loading
        }, 100)
    }

    override fun showError(error: Throwable) {
        error.message?.let { toast(it) }
        error.printStackTrace()
    }

    override fun showRepos(repos: ArrayList<Repository>) {
        adapter.run {
            this.repos.clear()
            this.repos.addAll(repos)
            this.notifyDataSetChanged()
        }
    }
}
