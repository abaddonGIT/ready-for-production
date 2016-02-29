package io.github.importre.kdr.app

import android.app.Application
import io.github.importre.kdr.di.component.AppComponent
import io.github.importre.kdr.di.component.DaggerAppComponent
import io.github.importre.kdr.di.module.ApiModule
import io.github.importre.kdr.di.module.PresenterModule

class App : Application() {

    lateinit var appComp: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComp = DaggerAppComponent.builder()
                .apiModule(ApiModule(applicationContext))
                .presenterModule(PresenterModule())
                .build()
    }
}