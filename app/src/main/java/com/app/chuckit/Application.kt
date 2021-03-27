package com.app.chuckit

import android.app.Application
import com.app.chuckit.di.*

val Application.component : AppComponents
    get() = DaggerAppComponents.builder()
        .appModule(AppModule(this, applicationContext))
        .remoteModule(RemoteModule())
        .dBModule(DBModule())
        .build()