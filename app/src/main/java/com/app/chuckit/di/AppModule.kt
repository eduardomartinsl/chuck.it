package com.app.chuckit.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.app.chuckit.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provê componentes que fazem jus a aplicação
 * [provideApp]
 * [provideContext]
 * [provideSharedPreferences]
 *
 */
@Module
open class AppModule(private val app: Application) {

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
            Constants.SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
}