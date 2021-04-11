package com.app.chuckit.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides Application Modules for the DI
 */
@Module
open class AppModule(private val app: Application, private val context: Context) {

    @Provides
    @Singleton
    open fun provideApp() = app

    @Provides
    @Singleton
    open fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    open fun provideSharedPreferences(): SharedPreferences =
        app.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
}