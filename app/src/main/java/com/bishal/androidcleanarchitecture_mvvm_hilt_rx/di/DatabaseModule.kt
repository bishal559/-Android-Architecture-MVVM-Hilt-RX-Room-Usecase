package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.di

import android.app.Application
import androidx.room.Room
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.AppDatabase
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.source.local.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    internal fun providePhotoDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao
    }
}