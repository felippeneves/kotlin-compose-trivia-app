package com.felippeneves.jettrivia.repository

import com.felippeneves.jettrivia.data.base.AnswerDatabaseDao
import com.felippeneves.jettrivia.model.Answer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnswerRepository @Inject constructor(private val answerDatabaseDao: AnswerDatabaseDao) {
    suspend fun addAnswer(answer: Answer) = answerDatabaseDao.insert(answer)
    suspend fun updateAnswer(answer: Answer) = answerDatabaseDao.update(answer)
    suspend fun deleteAnswer(answer: Answer) = answerDatabaseDao.deleteNote(answer)
    suspend fun deleteAllAnswer() = answerDatabaseDao.deleteAll()
    fun getAllAnswers(): Flow<List<Answer>> = answerDatabaseDao.getAll().flowOn(Dispatchers.IO)
        .conflate()
}