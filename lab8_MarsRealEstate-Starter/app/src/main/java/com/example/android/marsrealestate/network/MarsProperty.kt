package com.example.android.marsrealestate.network

import com.google.gson.annotations.SerializedName

data class MarsProperty(
        val id: String,
        @SerializedName("img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double
)
