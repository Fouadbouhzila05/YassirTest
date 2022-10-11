package com.fouadbouhzila.yassirtest.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieResponse (

    @SerializedName("results")
    val movise : List<Movie>

) : Parcelable {
            constructor() : this(mutableListOf())
}