package com.bishal.androidcleanarchitecture_mvvm_hilt_rx.di


import android.app.Application
import com.bishal.androidcleanarchitecture_mvvm_hilt_rx.data.persistence.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule {

    @Provides
    @Singleton
     fun providesSharedPrefs(application: Application): SharedPrefs = SharedPrefs.getInstance(application)
}