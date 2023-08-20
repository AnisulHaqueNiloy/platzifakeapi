package com.example.platzi_api.views.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load

import com.example.platzi_api.R
import com.example.platzi_api.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

lateinit var binding: FragmentProfileBinding
private val viewModel:ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel.getUserProfile()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profileResponse.observe(viewLifecycleOwner){
            if (it.isSuccessful){
                it.body()?.let {
                    binding.apply {
                       textViewName.text =it.name
                       textViewEmail.text =it.email
                       textViewRole.text =it.role
                        imageViewAvatar.load(it.avatar)

                    }

                }
            }
        }

    }


}