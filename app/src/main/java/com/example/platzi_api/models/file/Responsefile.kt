package com.example.platzi_api.models.file


import com.google.gson.annotations.SerializedName

data class Responsefile(
    @SerializedName("originalname")
    var originalname: String?,
    @SerializedName("filename")
    var filename: String?,
    @SerializedName("location")
    var location: String?
)