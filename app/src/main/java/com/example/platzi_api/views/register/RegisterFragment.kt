package com.example.platzi_api.views.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.platzi_api.databinding.FragmentRegisterBinding
import com.example.platzi_api.models.register.RequestRegister
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater,container,false)

        viewModel.registereResponse.observe(viewLifecycleOwner){
                if (it.isSuccessful){
                    Log.d("TAG","DATA: ${it.body()}")
                }
            }

        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener { 
            val name =binding.userName.text.toString().trim()
            val email =binding.userEmail.text.toString().trim()
            val password =binding.userPassword.text.toString().trim()
            
            handleRegister(name,email,password)
            
        }
        
    }

    private fun handleRegister(name: String, email: String, password: String) {

        val request =RequestRegister(name = name, email = email, password = password, avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJOi2pyfg14hb1e3waXJ5mceQkH4froE5TgnVQDkZt&s")

        viewModel.register(request)
    }


}