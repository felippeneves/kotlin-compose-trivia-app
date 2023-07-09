package com.felippeneves.jettrivia.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.felippeneves.jettrivia.model.Answer
import com.felippeneves.jettrivia.util.UUIDConverter

@Database(
    entities = [
        Answer::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    UUIDConverter::class
)
abstract class TriviaDatabase : RoomDatabase() {
        abstract fun answerDao(): AnswerDatabaseDao

}