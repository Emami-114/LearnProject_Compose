package com.example.learnproject_compose.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.learnproject_compose.deutschTest.data.dao.DeQuizDao
import com.example.learnproject_compose.deutschTest.domain.model.DeQuiz
import com.example.learnproject_compose.dictionary.data.local.Converters
import com.example.learnproject_compose.dictionary.data.local.SaveWordsDao
import com.example.learnproject_compose.dictionary.domain.model.entity.WordDataEntity
import com.example.learnproject_compose.model.QuizModel

@Database(entities = [DeQuiz::class,QuizModel::class, WordDataEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Databases : RoomDatabase() {

    abstract val quizDao: Dao
    abstract val deQuizDao : DeQuizDao
    abstract val saveWordsDao: SaveWordsDao

}


