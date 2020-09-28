package com.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

// Mengubah nama pada field sama persis dengan key pada JSON
data class TourismResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("longitude")
    val longitude: Double,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("like")
    val like: Int,

    @field:SerializedName("image")
    val image: String
)

