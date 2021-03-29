package com.app.chuckit.di

import com.app.chuckit.ui.activities.ChuckNorrisFactsActivity
import com.app.chuckit.ui.activities.SearchChuckNorrisFactsActivity
import com.app.chuckit.viewModels.ChuckNorrisFactsViewModel
import com.app.chuckit.viewModels.SearchChuckNorrisFactsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, DBModule::class])
interface AppComponents {
    //--- activities
    fun inject(activity: ChuckNorrisFactsActivity)
    fun inject(activity: SearchChuckNorrisFactsActivity)

    //--- viewModels
    fun inject(viewModel: ChuckNorrisFactsViewModel)
    fun inject(viewModel: SearchChuckNorrisFactsViewModel)
}