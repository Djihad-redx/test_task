package com.example.test_tasks.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_tasks.R
import com.example.test_tasks.view_models.QuestionsViewModel
import com.example.test_tasks.data.models.Question
import com.example.test_tasks.databinding.FragmentQuestionsBinding
import com.example.test_tasks.adapters.QuestionAdapter
import com.example.test_tasks.utilities.Constants.NEW_GAME
import com.example.test_tasks.utilities.Helpers.toastMe


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private val viewModel : QuestionsViewModel by viewModels()
    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var questionAdapter: QuestionAdapter


    private val question1 = Question( "are you happy?",false)
    private val question2 = Question( "do u like programing?",false)
    private val question3 = Question( "are you working ?",false)
    private val question4 = Question( "are you single?",false)
    private val question5 = Question( "do you like sport?",false)

    private var isFirstTime = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        setupRecyclerView()

        binding.button2.setOnClickListener(View.OnClickListener {
            viewModel.deleteAll()
            setQuestions()
        })

     viewModel.questions.observe(viewLifecycleOwner, Observer { result ->
         questionAdapter.submitList(result)
     })
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.questionsResult


    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        questionAdapter = QuestionAdapter()
        adapter = questionAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setQuestions(){
        viewModel.setQuestions(question1)
        viewModel.setQuestions(question2)
        viewModel.setQuestions(question3)
        viewModel.setQuestions(question4)
        viewModel.setQuestions(question5)
        toastMe(requireContext(),NEW_GAME)
    }


}