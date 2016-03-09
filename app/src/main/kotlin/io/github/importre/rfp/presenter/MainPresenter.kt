package io.github.importre.rfp.presenter

import io.github.importre.rfp.api.Github
import io.github.importre.rfp.api.repo.Repository
import io.github.importre.rfp.intf.IScheduler
import io.github.importre.rfp.view.MainView
import rx.Observable
import rx.Subscriber
import rx.Subscription
import java.util.*

class MainPresenter(private val api: Github,
                    private val scheduler: IScheduler) {

    private var view: MainView? = null
    private var subscription: Subscription? = null
    private var request: Observable<ArrayList<Repository>>? = null

    fun setView(view: MainView) {
        this.view = view
    }

    fun stop() {
        this.view = null
    }

    /**
     * Loads **repositories** of https://github.com/[user]
     *
     * @param user Account name of [Github](https://github.com).
     * @param force `true` to load repositories by force. By default `false`.
     */
    @JvmOverloads
    fun loadRepos(user: String, force: Boolean = false) {
        if (force) {
            subscription?.unsubscribe()
            clear()
        }

        if (this.request == null) {
            request = getObservable(user)
        }
        subscription = request?.subscribe(getSubscriber())
    }

    private fun clear() {
        this.request = null
    }

    private fun getObservable(user: String): Observable<ArrayList<Repository>> {
        return api.getRepos(user)
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .distinctUntilChanged()
                .cache()
    }

    private fun getSubscriber(): Subscriber<ArrayList<Repository>> {
        return object : Subscriber<ArrayList<Repository>>() {
            override fun onStart() {
                view?.showLoading(true)
            }

            override fun onError(error: Throwable) {
                view?.showLoading(false)
                view?.showError(error)
            }

            override fun onCompleted() {
                view?.showLoading(false)
            }

            override fun onNext(result: ArrayList<Repository>?) {
                if (result == null) return
                result.sort()
                view?.showRepos(result)
            }
        }
    }
}