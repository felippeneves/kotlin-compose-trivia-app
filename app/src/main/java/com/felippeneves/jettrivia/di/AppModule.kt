package com.felippeneves.jettrivia.di

import android.content.Context
import androidx.room.Room
import com.felippeneves.jettrivia.data.base.AnswerDatabaseDao
import com.felippeneves.jettrivia.data.base.TriviaDatabase
import com.felippeneves.jettrivia.network.QuestionApi
import com.felippeneves.jettrivia.repository.AnswerRepository
import com.felippeneves.jettrivia.repository.QuestionRepository
import com.felippeneves.jettrivia.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideQuestionRepository(api: QuestionApi) = QuestionRepository(api)

    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAnswersDao(triviaDatabase: TriviaDatabase): AnswerDatabaseDao =
        triviaDatabase.answerDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): TriviaDatabase =
        Room.databaseBuilder(
            context,
            TriviaDatabase::class.java,
            "trivia_db"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideAnswerRepository(dao: AnswerDatabaseDao) = AnswerRepository(dao)

}