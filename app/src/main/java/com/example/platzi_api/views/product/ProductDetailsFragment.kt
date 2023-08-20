package com.example.platzi_api.views.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.example.platzi_api.R
import com.example.platzi_api.databinding.FragmentProductDetailsBinding
import com.example.platzi_api.models.product.ResponseProduct
import com.example.platzi_api.models.product.ResponseProductItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    lateinit var binding: FragmentProductDetailsBinding
    val viewModel : ProductViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)

        requireArguments().getInt("id").let {
            viewModel.getProductsById(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.responseProductById.observe(viewLifecycleOwner){
            if (it.isSuccessful){
setProduct(it.body()!!)
            }
        }

    }

    private fun setProduct(it: ResponseProductItem){
        binding.titleTextView.text =it.title
        binding.descriptionTextView.text =it.description
        binding.priceTextView.text = "Price: ${it.price}"
        it.images?.get(0)?.let { img_url ->
            binding.image1.load(img_url)

        }

        it.images?.get(1)?.let { img_url ->
            binding.image2.load(img_url)

        }
        it.images?.get(2)?.let { img_url ->
            binding.image3.load(img_url)

        }

    }


}