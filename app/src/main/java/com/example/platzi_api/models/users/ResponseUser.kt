package com.example.platzi_api.models.users


import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)