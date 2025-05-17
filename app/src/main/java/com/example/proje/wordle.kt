package com.example.proje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.proje.databinding.FragmentLoginBinding
import com.example.proje.databinding.FragmentWordleBinding


class wordle : Fragment() {
    private var _binding:FragmentWordleBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

 _binding = FragmentWordleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // 2 sütunlu ızgara
        recyclerView.adapter = WordAdapter() // Buraya uygun adapter’ı bağla
    }
    override fun onDestroy() {
        super.onDestroy()
    }


}