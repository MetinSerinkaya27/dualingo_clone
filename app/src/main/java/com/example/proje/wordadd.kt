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
import com.example.proje.databinding.FragmentLoginBinding
import com.example.proje.databinding.FragmentWordadd2Binding
import kotlinx.coroutines.launch

class wordadd : Fragment() {

    private var _binding: FragmentWordadd2Binding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentWordadd2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button6.setOnClickListener {
        kaydet(it)
        }
    }
    fun kaydet(view: View){
        val EngWordName=binding.editTextText2.text.toString()
        val TurWordName=binding.editTextText3.text.toString()
        val Word=Word(EngWordName=EngWordName, TurWordName = TurWordName)
        if (EngWordName.isEmpty()||TurWordName.isEmpty()){
            Toast.makeText(requireContext(),"Lütfen Boş Bırakmayınız",Toast.LENGTH_LONG).show()
        }
        else{
            lifecycleScope.launch {
                val db=WordDatabase.getDatabase(requireContext())
                db.WordDao().insert(Word)

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