package com.example.platzi_api.views.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.platzi_api.models.file.Responsefile
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.product.ResponseProduct
import com.example.platzi_api.models.product.ResponseProductItem
import com.example.platzi_api.models.users.ResponseUser
import com.example.platzi_api.repositories.AuthRepo
import com.example.platzi_api.repositories.ProductRepo
import com.example.platzi_api.repositories.UserRepo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repo: ProductRepo):ViewModel(){

    private val _response = MutableLiveData<Response<ResponseProduct>>()
    val registerProduct : LiveData<Response<ResponseProduct>> = _response

    fun getAllProducts(){

        viewModelScope.launch {
            val data = repo.getAllProducts()
            _response.postValue(data)
        }


    }

    private val _responseBYID = MutableLiveData<Response<ResponseProductItem>>()
    val responseProductById : LiveData<Response<ResponseProductItem>> = _responseBYID

    fun getProductsById(id: Int){

        viewModelScope.launch {
            val data = repo.getProductsById(id)
            _responseBYID.postValue(data)
        }


    }

    private val _responseFile = MutableLiveData<Response<Responsefile>>()

    val uploadResponse: LiveData<Response<Responsefile>>  = _responseFile
     fun uploadFile(part: MultipartBody.Part){
         viewModelScope.launch {
             _responseFile.postValue(repo.uploadFile(part))
         }

}
    val pagingData = repo.getPagingProducts().cachedIn(viewModelScope)

}