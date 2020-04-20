package com.example.ooh_mdb.presentation

import android.app.Application
import com.example.ooh_mdb.presentation.di.applicationModule
import org.koin.android.ext.android.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
    }
}
