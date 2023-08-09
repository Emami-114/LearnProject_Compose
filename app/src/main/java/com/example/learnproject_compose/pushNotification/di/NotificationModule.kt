package com.example.learnproject_compose.pushNotification.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.learnproject_compose.pushNotification.core.Preferences
import com.example.learnproject_compose.pushNotification.data.remote.FirebaseCloudMessagingApi
import com.example.learnproject_compose.pushNotification.data.remote.FirebaseCloudMessagingApi.Companion.BASE_URL
import com.example.learnproject_compose.pushNotification.data.repository.FirebaseCloudMessagingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideFirebaseCloudMessagingApi(): FirebaseCloudMessagingApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FirebaseCloudMessagingApi::class.java)

    @Provides
    @Singleton
    fun provideFirebaseCloudMessagingRepository(api: FirebaseCloudMessagingApi): FirebaseCloudMessagingRepository =
        FirebaseCloudMessagingRepository(api)

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(Preferences.PREFS, Context.MODE_PRIVATE)

}