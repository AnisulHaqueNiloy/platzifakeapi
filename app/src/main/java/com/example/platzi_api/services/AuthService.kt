package com.example.platzi_api.services

import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    //authentication for login and registration f

    @POST("auth/login")
    suspend fun login(@Body request: RequestLogin): Response<ResponseLogin>

    @POST("users")
    suspend fun register(@Body request : RequestRegister) : Response<ResponseRegister>



}