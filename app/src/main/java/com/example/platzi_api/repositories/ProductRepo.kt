package com.example.platzi_api.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.platzi_api.models.file.Responsefile
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.product.ResponseProduct
import com.example.platzi_api.models.product.ResponseProductItem
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import com.example.platzi_api.paging.ProductPagingSource
import com.example.platzi_api.services.AuthService
import com.example.platzi_api.services.ProductService
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class ProductRepo @Inject constructor(private val service: ProductService) {

    suspend fun getAllProducts(): Response<ResponseProduct>{
        return service.getAllProducts()
    }


    suspend fun getProductsById(id: Int): Response<ResponseProductItem>{
        return service.getProductsById(id)
    }

    suspend fun uploadFile(part: MultipartBody.Part): Response<Responsefile>{
        return service.uploadFile(part)
    }

     fun getPagingProducts() =Pager(
            config = PagingConfig(pageSize = 10, initialLoadSize = 10),
            pagingSourceFactory = {
               ProductPagingSource(service)
            }
        ).liveData



    }

