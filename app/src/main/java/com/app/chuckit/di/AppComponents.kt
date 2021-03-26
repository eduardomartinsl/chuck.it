package com.app.chuckit.di

import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RemoteModule::class), (DBModule::class)])
interface AppComponents {

}