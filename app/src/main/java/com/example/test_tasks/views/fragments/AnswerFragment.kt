package com.example.test_tasks.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.test_tasks.R
import com.example.test_tasks.data.models.Question
import com.example.test_tasks.databinding.FragmentAnswerBinding
import com.example.test_tasks.view_models.AnswerViewModel

import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
class AnswerFragment : Fragment(R.layout.fragment_answer) {
    lateinit var binding: FragmentAnswerBinding
    val args: AnswerFragmentArgs by navArgs()
    val viewModel:AnswerViewModel by viewModels()
    var question:Question? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnswerBinding.inflate(inflater, container, false)
        viewModel.getQuestion(args.questionArg)
        viewModel.question.observe(viewLifecycleOwner, Observer {
            binding.question.text = it.question
            question = it
        })

        binding.btYes.setOnClickListener(View.OnClickListener { view->
            viewModel.updateQuestion(question!!)
            view.findNavController().popBackStack()

        })
        return binding.root
    }

}