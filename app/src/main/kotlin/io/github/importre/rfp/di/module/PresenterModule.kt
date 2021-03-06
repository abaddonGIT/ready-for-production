package io.github.importre.rfp.di.module

import dagger.Module
import dagger.Provides
import io.github.importre.rfp.api.Github
import io.github.importre.rfp.intf.IScheduler
import io.github.importre.rfp.presenter.MainPresenter
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Singleton

@Module
class PresenterModule() {

    @Provides
    fun provideScheduler(): IScheduler = object : IScheduler {
        override fun main(): Scheduler = AndroidSchedulers.mainThread()
        override fun background() = Schedulers.newThread()
    }

    @Provides
    @Singleton
    fun provideMainPresenter(api: Github, scheduler: IScheduler): MainPresenter {
        return MainPresenter(api, scheduler)
    }
}
