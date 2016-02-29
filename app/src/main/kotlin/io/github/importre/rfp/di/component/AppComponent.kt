package io.github.importre.rfp.di.component

import dagger.Component
import io.github.importre.rfp.di.module.ApiModule
import io.github.importre.rfp.di.module.PresenterModule
import io.github.importre.rfp.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class, PresenterModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)
}