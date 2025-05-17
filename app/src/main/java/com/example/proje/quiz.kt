package com.example.proje

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.proje.databinding.FragmentMenuBinding
import com.example.proje.databinding.FragmentQuizBinding
import kotlinx.coroutines.launch

class quiz : Fragment() {
    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!
    private lateinit var Wordlist: List<Word>
var maxkelime=0
    var kelime=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button12.setOnClickListener {
            val sayitext=binding.editTextText7.text.toString().trim()
            maxkelime=sayitext.toInt()
            lifecycleScope.launch {
                val db=WordDatabase.getDatabase(requireContext())
                Wordlist=db.WordDao().getwordrand(maxkelime)
                kelimegoster()
            }
        }


binding.button10.setOnClickListener {
     var tahmin=binding.editTextText4.text.toString()
    var cevap=Wordlist[kelime].TurWordName
    if (tahmin==cevap){
        Toast.makeText(requireContext(),"dogru",Toast.LENGTH_SHORT).show()
    }
    else{
        Toast.makeText(requireContext(),"yanlıs",Toast.LENGTH_SHORT).show()
    }
    kelime++
    if (kelime<Wordlist.size){
        binding.editTextText4.text.clear()
kelimegoster()
    }
    else{
        Toast.makeText(requireContext(),"bitti",Toast.LENGTH_SHORT).show()
    }
}

    }
fun kelimegoster(){
    binding.textView.text=Wordlist[kelime].EngWordName
}


    override fun onDestroy() {
        super.onDestroy()
    }
}