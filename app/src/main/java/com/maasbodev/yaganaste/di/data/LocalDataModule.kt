package com.maasbodev.yaganaste.di.data

import android.app.Application
import androidx.room.Room
import com.maasbodev.yaganaste.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    fun appDatabaseProvider(application: Application): AppDatabase =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "banks-db"
        ).build()
}
