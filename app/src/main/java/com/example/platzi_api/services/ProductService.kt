package com.example.platzi_api.services

import com.example.platzi_api.models.file.Responsefile
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.product.ResponseProduct
import com.example.platzi_api.models.product.ResponseProductItem
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import com.example.platzi_api.models.users.ResponseUser
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): Response<ResponseProduct>


    @GET("products/")
    suspend fun getPagingProducts(
        @Query("offset") offset:Int, @Query("limit")limit:Int): List<ResponseProductItem>

    @GET("products/{id}")
    suspend fun getProductsById(@Path("id") id : Int): Response<ResponseProductItem>


    @Multipart
    @POST("files/upload")
    suspend fun uploadFile(@Part file: MultipartBody.Part): Response<Responsefile>

}