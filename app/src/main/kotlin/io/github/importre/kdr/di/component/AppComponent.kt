package io.github.importre.kdr.di.component

import dagger.Component
import io.github.importre.kdr.di.module.ApiModule
import io.github.importre.kdr.di.module.PresenterModule
import io.github.importre.kdr.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class, PresenterModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)
}