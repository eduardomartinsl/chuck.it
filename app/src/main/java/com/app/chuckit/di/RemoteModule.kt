package com.app.chuckit.di

import com.app.chuckit.BuildConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provê componentes que fazem jus a consumo de api
 * [proveRetrofit]
 *
 */
@Module
class RemoteModule {

    // TODO: Inserir API URL no BuildConfig
    @Provides
    @Singleton
    fun proveRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("BuildConfig.API_URL")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}