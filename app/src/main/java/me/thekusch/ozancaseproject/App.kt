package me.thekusch.ozancaseproject

import android.app.Application
import me.thekusch.ozancaseproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            modules(
                listOf(
                    dataModule,
                    serviceModule,
                    repositoryModule,
                    domainModule,
                    viewModelModule
                )
            )
        }
    }
}