package com.example.proje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proje.databinding.FragmentStartQuizBinding

class StartQuizFragment : Fragment() {

    private var _binding: FragmentStartQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartQuiz.setOnClickListener {
            val countText = binding.inputQuestionCount.text.toString().trim()
            val questionCount = countText.toIntOrNull()

            if (questionCount != null && questionCount > 0) {
                val action = StartQuizFragmentDirections.actionStartQuizFragmentToQuizFragment(questionCount)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Geçerli bir sayı girin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
