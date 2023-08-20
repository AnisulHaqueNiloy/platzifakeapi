package com.example.platzi_api.models.product


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)