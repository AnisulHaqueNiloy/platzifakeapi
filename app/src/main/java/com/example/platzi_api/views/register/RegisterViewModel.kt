package com.example.platzi_api.views.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.register.RequestRegister
import com.example.platzi_api.models.register.ResponseRegister
import com.example.platzi_api.repositories.AuthRepo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: AuthRepo):ViewModel(){

    private val _responseRegister = MutableLiveData<Response<ResponseRegister>>()
    val registereResponse: LiveData<Response<ResponseRegister>> = _responseRegister


    fun register(request : RequestRegister){
        viewModelScope.launch {
            val data = repo.register(request)
            _responseRegister.postValue(data)
        }




    }

}