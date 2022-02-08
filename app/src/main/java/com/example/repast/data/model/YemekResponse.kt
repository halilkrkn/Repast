package com.example.repast.data.model


import com.google.gson.annotations.SerializedName

data class YemekResponse(

    val success: Int,
    val yemekler: List<Yemekler>

)