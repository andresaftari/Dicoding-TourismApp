package com.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

// Mengubah nama pada field sama persis dengan key pada JSON
data class ListTourismResponse(
       @field:SerializedName("error")
       val error: Boolean,

       @field:SerializedName("message")
       val message: String,

       @field:SerializedName("places")
       val places: List<TourismResponse>
)