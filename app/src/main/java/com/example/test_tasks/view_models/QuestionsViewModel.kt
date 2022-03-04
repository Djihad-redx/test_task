package com.example.test_tasks.view_models

import android.util.Log
import androidx.lifecycle.*
import com.example.test_tasks.data.models.Question
import com.example.test_tasks.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel
    @Inject constructor(
        val repo:MainRepository
    )
    : ViewModel() {
    var questions = MediatorLiveData<List<Question>>()
    val questionsResult = repo.getQuestions()
    init {

        questions = MediatorLiveData<List<Question>>()
        questions.addSource(questionsResult){ result->
            result?.let { questions.value = it }
        }
    }

    fun setQuestions(question: Question) = viewModelScope.launch {
            repo.insertQuestion(question)
    }

    fun deleteAll()= viewModelScope.launch {
       repo.deleteAll()
   }

    fun updateQuestion(newQuestion: Question) = viewModelScope.launch {
        repo.updateQuestion(newQuestion)
    }


}
