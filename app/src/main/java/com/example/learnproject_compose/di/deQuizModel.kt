package com.example.learnproject_compose.di

import com.example.learnproject_compose.deutschTest.data.repository.DeQuizRepositoryImpl
import com.example.learnproject_compose.deutschTest.domain.repository.DeQuizRepository
import com.example.learnproject_compose.local.Databases
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object deQuizModel {
    @Provides
    @Singleton
    fun provideDeQuizRef() = Firebase.firestore.collection("de_test")

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideDeQuizRepository(deQuizRef: CollectionReference, db: Databases): DeQuizRepository =
        DeQuizRepositoryImpl(deQuizRef, db)


}