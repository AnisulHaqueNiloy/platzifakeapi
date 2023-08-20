package com.example.platzi_api.repositories

import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import com.example.platzi_api.models.users.ResponseUser
import com.example.platzi_api.services.AuthService
import com.example.platzi_api.services.UserService
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(private val service: UserService) {

    suspend fun getUserProfile(): Response<ResponseUser>{
        return service.getUserProfile()
    }



}