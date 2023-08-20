package com.example.platzi_api.models.register


import com.google.gson.annotations.SerializedName

data class RequestRegister(
    @SerializedName("name")
    var name: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("avatar")
    var avatar: String?
)