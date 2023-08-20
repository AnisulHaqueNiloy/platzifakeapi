package com.example.platzi_api.models.register


import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)