package com.example.proje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.proje.databinding.FragmentLoginBinding
import com.example.proje.databinding.FragmentMenuBinding


class menu : Fragment() {
    private var _binding: FragmentMenuBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button7.setOnClickListener {
            word(it)
        }
        binding.button8.setOnClickListener {
            wordle(it)
        }
        binding.button9.setOnClickListener {
            quiz(it)
        }
    }
    fun word(view: View){
val action=menuDirections.actionMenuToWords()
        Navigation.findNavController(view).navigate(action)
    }
    fun wordle(view: View){
val action=menuDirections.actionMenuToWordle()
        Navigation.findNavController(view).navigate(action)
    }
    fun quiz(view: View){
val action=menuDirections.actionMenuToQuiz()
        Navigation.findNavController(view).navigate(action)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}