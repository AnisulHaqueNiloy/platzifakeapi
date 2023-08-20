package com.example.platzi_api.di

import com.example.platzi_api.services.AuthService
import com.example.platzi_api.services.ProductService
import com.example.platzi_api.services.UserService
import com.example.platzi_api.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
    }
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit.Builder): AuthService{
        return retrofit.build().create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providesHttpClient(interceptor: AuthInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit.Builder,client: OkHttpClient): UserService{
        return retrofit.client(client).build().create(UserService::class.java)
    }
    @Provides
    @Singleton
    fun providesProductService(retrofit: Retrofit.Builder): ProductService{
        return retrofit.build().create(ProductService::class.java)
    }


}