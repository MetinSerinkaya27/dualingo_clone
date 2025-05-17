package com.example.proje

import android.app.DirectAction
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.proje.databinding.ActivityMainBinding.inflate
import com.example.proje.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class login : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            giris(it)
        }
        binding.button2.setOnClickListener {
        kayıt(it)
        }
        binding.button14.setOnClickListener {
            rest(it)
        }
    }
    fun giris(view: View){
val name=binding.editTextText.text.toString()
val password=binding.editTextTextPassword.text.toString()
        lifecycleScope.launch{
            val db = UserDatabase.getDatabase(requireContext())
            val user=db.userdao().login(name,password)
            if (user!=null){
                Toast.makeText(requireContext(),"Başaralı Giriş",Toast.LENGTH_LONG).show()
                val action=loginDirections.actionLoginToMenu()
                Navigation.findNavController(view).navigate(action)
            }
            else{
                Toast.makeText(requireContext(),"Başarısız",Toast.LENGTH_LONG).show()
            }
        }
    }
    fun kayıt(view: View){
        val name=binding.editTextText.text.toString()
        val password=binding.editTextTextPassword.text.toString()
        val user=user(name=name, password = password)
        lifecycleScope.launch {
            val db = UserDatabase.getDatabase(requireContext())
            db.userdao().insertUser(user)
        }
    }
    fun rest(view: View){
        val action=loginDirections.actionLoginToRest()
        Navigation.findNavController(view).navigate(action)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}