package com.example.proje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.proje.databinding.FragmentDeleteBinding
import com.example.proje.databinding.FragmentQuizBinding
import kotlinx.coroutines.launch

class delete : Fragment() {
    private var _binding: FragmentDeleteBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button11.setOnClickListener {
          sil(it)
        }


    }
    fun sil(view: View){
        val silenecek=binding.editTextText5.text.toString()

        lifecycleScope.launch {
            val db=WordDatabase.getDatabase(requireContext())
            val kelime=db.WordDao().find(silenecek)
            if (kelime==null){
                Toast.makeText(requireContext(),"Sisteme Kayıtlı Boyle Bir Kelime Bulunmamaktadır...",Toast.LENGTH_SHORT).show()
            }
            else{
                db.WordDao().delete(kelime)
                Toast.makeText(requireContext(),"Sistemden Başarıyla Silinmiştir ...",Toast.LENGTH_SHORT).show()
                val viewModel = ViewModelProvider(requireActivity())[WordViewModel::class.java]
                viewModel.refreshWords()
                findNavController().popBackStack()

            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}