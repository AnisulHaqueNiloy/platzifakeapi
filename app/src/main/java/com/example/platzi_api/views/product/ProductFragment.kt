package com.example.platzi_api.views.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.platzi_api.R
import com.example.platzi_api.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment(), ProductAdapter.Listener, ProductPagingAdapter.Listener {

    lateinit var binding: FragmentProductBinding
    val viewModel: ProductViewModel by viewModels()
    lateinit var adapter: ProductAdapter
    lateinit var adapterPaging: ProductPagingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater,container,false)

        adapter = ProductAdapter(this)
        adapterPaging = ProductPagingAdapter(this
        )
        binding.productRCV.adapter = adapterPaging
        //viewModel.getAllProducts()
        viewModel.pagingData.observe(viewLifecycleOwner){

            adapterPaging.submitData(lifecycle,it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myProfileBtn.setOnClickListener{

            findNavController().navigate(R.id.action_productFragment_to_profileFragment)
        }

        binding.productBtnupload.setOnClickListener{

            findNavController().navigate(R.id.action_productFragment_to_productUploadFragment)
        }

        //viewModel.registerProduct.observe(viewLifecycleOwner){
        //    if (it.isSuccessful){
        //       adapter.submitList(it.body())
        //      binding.productRCV.adapter = adapter
        //  }
        //}



    }

    override fun productClick(productId: Int) {
        var bundle = Bundle ()
        bundle.putInt("id",productId)

        findNavController().navigate(R.id.action_productFragment_to_productDetailsFragment, bundle)


    }


}