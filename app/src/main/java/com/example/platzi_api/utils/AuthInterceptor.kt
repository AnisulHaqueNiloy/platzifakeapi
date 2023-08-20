package com.example.platzi_api.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private var prefManager: PrefManager) :Interceptor
{

//    @Inject
//    lateinit var prefManager: PrefManager
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer ${prefManager.getPref(KEY_ACCESSTOKEN)}")

        return chain.proceed(request.build())
    }


}