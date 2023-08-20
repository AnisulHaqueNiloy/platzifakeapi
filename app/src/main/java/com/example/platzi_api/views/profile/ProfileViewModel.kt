package com.example.platzi_api.views.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platzi_api.models.login.RequestLogin
import com.example.platzi_api.models.login.ResponseLogin
import com.example.platzi_api.models.users.ResponseUser
import com.example.platzi_api.repositories.AuthRepo
import com.example.platzi_api.repositories.UserRepo
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: UserRepo):ViewModel(){

    private val _response = MutableLiveData<Response<ResponseUser>>()
    val profileResponse : LiveData<Response<ResponseUser>> = _response

    fun getUserProfile(){

        viewModelScope.launch {
            val data = repo.getUserProfile()
            _response.postValue(data)
        }


    }

}