package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Domain model untuk memisahkan TourismEntity (Room model) dari Android framework
@Parcelize
data class Tourism(
   val tourismId: String,
   val description: String,
   val name: String,
   val address: String,
   val latitude: Double,
   val longitude: Double,
   val like: Int,
   val image: String,
   val isFavorite: Boolean
) : Parcelable