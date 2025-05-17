package com.example.proje

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proje.databinding.FragmentQuizBinding
import kotlinx.coroutines.launch

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private lateinit var wordList: List<Word>
    private var currentIndex = 0
    private var questionCount = 0
    private var correctCount = 0
    private var incorrectCount = 0

    private val args: QuizFragmentArgs by navArgs() // Safe Args ile gelen argümanı al

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionCount = args.questionCount // gelen argümanı alıyoruz

        lifecycleScope.launch {
            val db = WordDatabase.getDatabase(requireContext())
            wordList = db.WordDao().getwordrand(questionCount)

            currentIndex = 0
            correctCount = 0
            incorrectCount = 0

            binding.textExplanation.text = ""
            binding.textStats.text = ""
            binding.inputAnswer.text.clear()
            showNextWord()
        }

        binding.buttonNext.setOnClickListener {
            val userAnswer = binding.inputAnswer.text.toString().trim()
            val correctAnswer = wordList[currentIndex].TurWordName.trim()

            if (userAnswer.equals(correctAnswer, ignoreCase = true)) {
                correctCount++
                binding.textExplanation.text = "✅ Doğru!"
            } else {
                incorrectCount++
                binding.textExplanation.text = "❌ Yanlış. Doğru cevap: $correctAnswer"
            }

            currentIndex++
            if (currentIndex < wordList.size) {
                binding.inputAnswer.text.clear()
                showNextWord()
            } else {
                binding.textStats.text = "✅ Doğru: $correctCount |❌ Yanlış: $incorrectCount"
                Toast.makeText(requireContext(), "Quiz tamamlandı!", Toast.LENGTH_LONG).show()
            }
        }

        // Soru sayısı giriş ve başlat butonunu gizle çünkü artık dışarıdan alıyoruz
        binding.buttonStartQuiz.visibility = View.GONE
        binding.inputQuestionCount.visibility = View.GONE
    }

    private fun showNextWord() {
        binding.textQuestion.text = wordList[currentIndex].EngWordName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
