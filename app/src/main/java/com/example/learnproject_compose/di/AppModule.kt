package com.example.learnproject_compose.di

import android.app.Application
import androidx.room.Room
import com.example.learnproject_compose.QuizRepository
import com.example.learnproject_compose.deutschTest.data.dao.DeQuizDao
import com.example.learnproject_compose.deutschTest.data.repository.DeQuizRepositoryImpl
import com.example.learnproject_compose.deutschTest.domain.repository.DeQuizRepository
import com.example.learnproject_compose.dictionary.data.local.SaveWordsDao
import com.example.learnproject_compose.dictionary.data.local.SavedWordsRepoImpl
import com.example.learnproject_compose.dictionary.data.repo.WordDataRepoImpl
import com.example.learnproject_compose.dictionary.domain.repository.SaveWordsRepo
import com.example.learnproject_compose.dictionary.domain.repository.WordDataRepo
import com.example.learnproject_compose.dictionary.domain.use_case.AddIntoSaved
import com.example.learnproject_compose.dictionary.domain.use_case.GetSavedWords
import com.example.learnproject_compose.dictionary.domain.use_case.GetWordData
import com.example.learnproject_compose.dictionary.domain.use_case.IsExistWord
import com.example.learnproject_compose.dictionary.domain.use_case.RemoveFromSaved
import com.example.learnproject_compose.dictionary.remote.DictionaryApiService
import com.example.learnproject_compose.local.Dao
import com.example.learnproject_compose.local.Databases
import com.example.learnproject_compose.missing_word.data.repository.MissingWordRepoImpl
import com.example.learnproject_compose.missing_word.local.repository.MissingWordRepo
import com.example.learnproject_compose.missing_word.peresentation.MissingWordViewModel
import com.example.learnproject_compose.screen.home.slider.SliderWordRepo
import com.example.learnproject_compose.swipeableCards.local.repository.CardRepository
import com.example.learnproject_compose.swipeableCards.repositoryImp.CardRepoImpl
import com.google.firebase.firestore.CollectionReference
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
object AppModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApiService {
        return Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSavedWordsDao(db: Databases): SaveWordsDao {
        return db.saveWordsDao
    }

    @Provides
    @Singleton
    fun provideWordDataRepo(
        dictionaryApiService: DictionaryApiService,
        dao: SaveWordsDao
    ): WordDataRepo {
        return WordDataRepoImpl(dictionaryApiService, dao)
    }


    @Provides
    @Singleton
    fun provideSavedRepo(db: Databases): SaveWordsRepo {
        return SavedWordsRepoImpl(db.saveWordsDao)
    }

    @Provides
    @Singleton
    fun provideGetWordDataUseCase(wordDataRepo: WordDataRepo): GetWordData {
        return GetWordData(wordDataRepo)
    }

    @Provides
    @Singleton
    fun provideGetSavedWordsUseCase(saveWordsRepo: SaveWordsRepo): GetSavedWords {
        return GetSavedWords(saveWordsRepo)
    }

    @Provides
    @Singleton
    fun provideAddIntoSavedUseCase(saveWordsRepo: SaveWordsRepo): AddIntoSaved {
        return AddIntoSaved(saveWordsRepo)
    }


    @Provides
    @Singleton
    fun provideRemoveFromSavedUseCase(saveWordsRepo: SaveWordsRepo): RemoveFromSaved {
        return RemoveFromSaved(saveWordsRepo)
    }

    @Provides
    @Singleton
    fun provideIsExistWordUseCase(saveWordsRepo: SaveWordsRepo): IsExistWord {
        return IsExistWord(saveWordsRepo)
    }

    @Provides
    @Singleton
    fun providerQuizDatabase(app: Application): Databases {
        return Room.databaseBuilder(
            app, Databases::class.java,
            "learn_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMissWordRepo(): MissingWordRepo = MissingWordRepoImpl()


    @Provides
    @Singleton
    fun provideCardsSetRepo(): CardRepository = CardRepoImpl()


    @Provides
    @Singleton
    fun provideSliderWordRepo(): SliderWordRepo = SliderWordRepo()

    @Provides
    @Singleton
    fun provideQuizRepository(db: Databases): QuizRepository {
        return QuizRepository(db)
    }

    @Provides
    @Singleton
    fun providerDao(db: Databases): Dao {
        return db.quizDao
    }

    @Provides
    @Singleton
    fun providerDeQuizDao(db: Databases): DeQuizDao {
        return db.deQuizDao
    }

}