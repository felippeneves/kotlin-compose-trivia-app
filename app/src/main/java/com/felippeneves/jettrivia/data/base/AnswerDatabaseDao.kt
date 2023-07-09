package com.felippeneves.jettrivia.data.base

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.felippeneves.jettrivia.model.Answer
import kotlinx.coroutines.flow.Flow

@Dao
interface AnswerDatabaseDao {
    @Query("" +
            "   SELECT *            " +
            "   FROM TB_ANSWERS     " +
    "")
    fun getAll(): Flow<List<Answer>>

    @Query("" +
            "   SELECT *                " +
            "   FROM TB_ANSWERS         " +
            "   WHERE ANSWER_ID = :id   " +
    "")
    suspend fun getAnswerById(id: String): Answer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(answer: Answer)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(answer: Answer)

    @Query("" +
            "   DELETE              " +
            "   FROM TB_ANSWERS     " +
            "")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(answer: Answer)
}