package com.example.test_tasks.view_models

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_tasks.data.models.Question
import com.example.test_tasks.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnswerViewModel
@Inject constructor(
    val repo: MainRepository
):ViewModel() {

    val question = MediatorLiveData<Question>()

     fun getQuestion(id:Int){
         val questionResult = repo.getQuestion(id)
        question.addSource(questionResult){ result->
            result?.let { question.value = it }
        }
    }
    fun updateQuestion(newQuestion: Question) = viewModelScope.launch {
        repo.updateQuestion(newQuestion)
    }

}