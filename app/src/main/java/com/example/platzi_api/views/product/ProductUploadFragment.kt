package com.example.platzi_api.views.product

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load

import com.example.platzi_api.databinding.FragmentProductUploadBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

import java.util.UUID


@AndroidEntryPoint
class ProductUploadFragment : Fragment() {


    lateinit var binding: FragmentProductUploadBinding
    val viewModel: ProductViewModel by viewModels()
    lateinit var imageUri:Uri
     var permmissionGranted: Boolean? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentProductUploadBinding.inflate(inflater,container,false)



        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.piv.setOnClickListener{
            ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(512, 512)  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }

        binding.uploadbtn.setOnClickListener{

        permmissionGranted?.let {
            if (it){
                uploadImage(imageUri)
            }
            else {
                Toast.makeText(requireContext(),"Permission Required",Toast.LENGTH_SHORT).show()
            }

        }




        }

        viewModel.uploadResponse.observe(viewLifecycleOwner){
            if (it.isSuccessful){
                Log.d("TAG","${it.body()}")
                binding.responseLayout.visibility = View.VISIBLE
                binding.pivonline.load(it.body()?.location)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CheckPermission()

    }

    private fun uploadImage(uri: Uri) {
        val fileDir = requireActivity().filesDir
        val file = File(fileDir, "p_${UUID.randomUUID()}.jpg")
        val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
        val outputStream =  FileOutputStream(file)

        inputStream?.copyTo(outputStream)
        val reqBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val part =MultipartBody.Part.createFormData("",file.name,reqBody)
        viewModel.uploadFile(part)
        inputStream?.close()
        outputStream.close()
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    imageUri = data?.data!!

                    binding.piv.setImageURI(imageUri)
                    binding.uploadbtn.visibility = View.VISIBLE

                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    fun CheckPermission(){
        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if(report.areAllPermissionsGranted() ){
                        permmissionGranted = true
                    }else{
                        permmissionGranted = false
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {

                }
            }).check()
    }


}