package com.example.platzi_api.views.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.repositories.AuthRepo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepo):ViewModel(){

    private val _response = MutableLiveData<Response<ResponseLogin>>()
    val loginResponse : LiveData<Response<ResponseLogin>> = _response

    fun login(requestLogin: RequestLogin){

        viewModelScope.launch {
            val data = repo.login(requestLogin)
            _response.postValue(data)
        }


    }

}