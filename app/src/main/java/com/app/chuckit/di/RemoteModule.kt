package com.app.chuckit.di

import com.app.chuckit.BuildConfig
import com.app.chuckit.services.NorrisService
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

    @Provides
    @Singleton
    fun proveRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNorrisService(retrofit: Retrofit): NorrisService =
        retrofit.create(NorrisService::class.java)
}