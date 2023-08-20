package com.example.platzi_api.repositories

import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import com.example.platzi_api.services.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthRepo @Inject constructor(private val service: AuthService) {

    suspend fun login(request: RequestLogin): Response<ResponseLogin>{
        return service.login(request)
    }

    suspend fun register(request: RequestRegister): Response<ResponseRegister>{
        return service.register(request)
    }

}