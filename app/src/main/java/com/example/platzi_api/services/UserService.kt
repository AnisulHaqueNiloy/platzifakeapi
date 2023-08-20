package com.example.platzi_api.services

import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import com.example.platzi_api.models.users.ResponseUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("auth/profile")
    suspend fun getUserProfile(): Response<ResponseUser>




}