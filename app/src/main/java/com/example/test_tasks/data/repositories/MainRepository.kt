package com.example.test_tasks.data.repositories

import com.example.test_tasks.data.models.Question
import com.example.test_tasks.data.db.QuestionDao
import javax.inject.Inject


class MainRepository @Inject constructor(
    val questionDao: QuestionDao
) {
    suspend fun insertQuestion(question: Question) = questionDao.insertQuestion(question)

    suspend fun  deleteQuestion(question: Question) = questionDao.deleteQuestion(question)

    suspend fun  updateQuestion(question: Question) = questionDao.updateQuestion(question)

    fun getQuestions() = questionDao.getAllQuestions()

    suspend fun deleteAll() = questionDao.deleteAll()

    fun getQuestion(id:Int)= questionDao.getQuestion(id)

}