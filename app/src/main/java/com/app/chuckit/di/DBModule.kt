package com.app.chuckit.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.app.chuckit.db.NorrisDatabase
import com.app.chuckit.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun providesDB(context: Context) =
        databaseBuilder(
            context,
            NorrisDatabase::class.java,
            Constants.CHUCKIT_DATABASE_NAME
        ).build()

    @Provides
    fun provodesNorrisDao(db: NorrisDatabase) = db.norrisDao()

}