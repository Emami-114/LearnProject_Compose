package com.example.learnproject_compose.di

import com.example.learnproject_compose.signIn_signUp.data.repository.AuthRepositoryImpl
import com.example.learnproject_compose.signIn_signUp.repository.AuthRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(auth = Firebase.auth)


}