package com.example.platzi_api.models.product


import com.google.gson.annotations.SerializedName

data class ResponseProductItem(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?,
    @SerializedName("category")
    var category: Category?
)