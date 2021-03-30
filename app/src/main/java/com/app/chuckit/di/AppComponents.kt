package com.app.chuckit.di

import com.app.chuckit.ui.activity.ChuckItActivity
import com.app.chuckit.viewModels.ChuckItViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, DBModule::class])
interface AppComponents {
    //--- activities
    fun inject(activity: ChuckItActivity)

    //--- viewModels
    fun inject(viewModel: ChuckItViewModel)

}