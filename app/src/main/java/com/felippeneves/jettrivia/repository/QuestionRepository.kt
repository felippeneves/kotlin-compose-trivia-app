package com.felippeneves.jettrivia.repository

import android.util.Log
import com.felippeneves.jettrivia.data.DataOrException
import com.felippeneves.jettrivia.model.QuestionItem
import com.felippeneves.jettrivia.network.QuestionApi
import java.lang.Exception
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestion()
            if (dataOrException.data.toString().isNotEmpty())
                dataOrException.loading = false
        } catch (ex: Exception) {
            dataOrException.e = ex
            Log.d("Exc", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }

        return dataOrException
    }
}