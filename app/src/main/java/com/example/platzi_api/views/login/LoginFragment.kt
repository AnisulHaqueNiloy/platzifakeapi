package com.example.platzi_api.views.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.platzi_api.R
import com.example.platzi_api.databinding.FragmentLoginBinding
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.utils.KEY_ACCESSTOKEN
import com.example.platzi_api.utils.KEY_REFRESHTOKEN
import com.example.platzi_api.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var prefManager: PrefManager




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        viewModel.loginResponse.observe(viewLifecycleOwner){
            if(it.isSuccessful){
                prefManager.setPref(KEY_ACCESSTOKEN,it.body()?.accessToken!!)
                prefManager.setPref(KEY_REFRESHTOKEN,it.body()?.refreshToken!!)
                Log.d("TAG","DATA: ${it.body()}")
                findNavController().navigate(R.id.action_loginFragment_to_productFragment)
            }

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPassword.text.toString().trim()
            handleLogin(email,password)

        }

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }



    }

    private fun handleLogin(email: String, password: String) {

        val request = RequestLogin(email = email, password = password)
        viewModel.login(request)

    }


}