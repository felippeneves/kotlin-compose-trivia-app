package com.felippeneves.jettrivia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felippeneves.jettrivia.data.DataOrException
import com.felippeneves.jettrivia.model.Answer
import com.felippeneves.jettrivia.model.QuestionItem
import com.felippeneves.jettrivia.repository.AnswerRepository
import com.felippeneves.jettrivia.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionRepository,
    private val repositoryAnswer: AnswerRepository
): ViewModel() {
    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }

    fun getTotalQuestionCount(): Int {
        return data.value.data?.toMutableList()?.size!!
    }

    fun addAnswer(indexQuestion: Int, choice: String) {
        viewModelScope.launch {
            repositoryAnswer.addAnswer(Answer(indexQuestion = indexQuestion, choice = choice))
        }
    }
}